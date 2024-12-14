package com.icia.movieinfo.service;

import com.icia.movieinfo.dao.MovieDao;
import com.icia.movieinfo.dto.MovieDto;
import com.icia.movieinfo.util.PagingUtil;
import jakarta.servlet.http.HttpSession;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Log
public class MovieService {
    @Autowired
    private MovieDao mDao;

    private ModelAndView mv;

    private int listCnt = 5;//페이지 당 출력할 게시글 개수

    public ModelAndView getMovieList(Integer pageNum,
                                     HttpSession session){
        log.info("getMovieList()");
        mv = new ModelAndView();

        if(pageNum == null){
            pageNum = 1;
        }

        Map<String, Integer> pmap = new HashMap<String, Integer>();
        pmap.put("pageNum", (pageNum - 1) * listCnt);
        pmap.put("listCnt", listCnt);

        List<MovieDto> mList = mDao.getMovieList(pmap);
        mv.addObject("mList", mList);

        //페이징 처리
        String pageHtml = getPaging(pageNum);
        mv.addObject("paging", pageHtml);

        session.setAttribute("pageNum", pageNum);
        mv.setViewName("list");

        return mv;
    }

    private String getPaging(Integer pageNum) {
        String pageHtml = null;

        //전체 글개수 구하기
        int maxNum = mDao.cntMovie();
        //한 페이지 당 보여질 페이지 번호의 개수
        int pageCnt = 5;

        PagingUtil paging = new PagingUtil(maxNum,
                pageNum, listCnt, pageCnt);

        pageHtml = paging.makePaging();

        return pageHtml;
    }

    public String insertMovie(List<MultipartFile> files,
                              MovieDto movie,
                              HttpSession session,
                              RedirectAttributes rttr) {
        log.info("insertMovie()");
        String msg = null;
        String view = null;
        String upFile = files.get(0).getOriginalFilename();

        try {
            if(!upFile.equals("")) {
                fileUpload(files, session, movie);
            }
            mDao.movieInsert(movie);
            view = "redirect:/?pageNum=1";
            msg = "작성 성공";
        } catch (Exception e){
            e.printStackTrace();
            view = "redirect:writeFrm";
            msg = "작성 실패";
        }

        rttr.addFlashAttribute("msg", msg);
        return view;
    }

    private void fileUpload(List<MultipartFile> files,
                            HttpSession session,
                            MovieDto movie) throws Exception {
        log.info("fileUpload()");
        String sysname = null;
        String oriname = null;

        String realPath = session.getServletContext().getRealPath("/");
        realPath += "upload/";
        File folder = new File(realPath);
        if(folder.isDirectory() == false){//폴더가 없을 경우 실행.
            folder.mkdir();//폴더 생성 메소드
        }

        MultipartFile mf = files.get(0);
        oriname = mf.getOriginalFilename();//업로드 파일명 가져오기

        sysname = System.currentTimeMillis()
                + oriname.substring(oriname.lastIndexOf("."));
        //업로드하는 파일을 upload 폴더에 저장.
        File file = new File(realPath + sysname);
        //파일 저장(upload 폴더)
        mf.transferTo(file);

        movie.setP_oriname(oriname);
        movie.setP_sysname(sysname);
    }

    public ModelAndView getMovie(Integer m_code) {
        log.info("getMovie()");
        mv = new ModelAndView();

        MovieDto movie = mDao.movieSelect(m_code);

        mv.addObject("movie", movie);
        return mv;
    }

    public String movieUpdate(List<MultipartFile> files,
                              MovieDto movie,
                              HttpSession session,
                              RedirectAttributes rttr) {
        log.info("movieUpdate()");
        String msg = null;
        String view = null;
        String poster = movie.getP_sysname();
        String upFile = files.get(0).getOriginalFilename();

        try {
            if(!upFile.equals("")) {
                fileUpload(files, session, movie);
            }
            mDao.movieUpdate(movie);

            if(poster != null && !upFile.equals("")) {
                fileDelete(poster, session);
            }

            view = "redirect:detail?m_code="+movie.getM_code();
            msg = "수정 성공";
        } catch (Exception e) {
            e.printStackTrace();
            view = "redirect:updateFrm?m_code="+movie.getM_code();
            msg = "수정 실패";
        }

        rttr.addFlashAttribute("msg", msg);
        return view;
    }

    private void fileDelete(String poster,
                            HttpSession session) throws Exception{
        log.info("fileDelete()");

        String realPath = session.getServletContext().getRealPath("/");
        realPath += "upload/" + poster;
        File file = new File(realPath);
        if(file.exists()){
            file.delete();//파일이 있으면 삭제
        }
    }

    //delete 메소드 추가
}
