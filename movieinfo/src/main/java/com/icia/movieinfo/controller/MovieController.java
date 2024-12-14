package com.icia.movieinfo.controller;

import com.icia.movieinfo.dto.MovieDto;
import com.icia.movieinfo.service.MovieService;
import jakarta.servlet.http.HttpSession;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@Log
public class MovieController {
    ModelAndView mv;

    @Autowired
    MovieService mServ;

    @GetMapping("/")
    public ModelAndView getList(Integer pageNum,
                                HttpSession session){
        log.info("getList()");
        mv = mServ.getMovieList(pageNum, session);
        return mv;
    }

    @GetMapping("writeFrm")
    public String writeFrm(){
        log.info("writeFrm()");
        return "writeFrm";
    }

    @PostMapping("writeProc")
    public String writeProc(@RequestPart List<MultipartFile> files,
                            MovieDto movie,
                            HttpSession session,
                            RedirectAttributes rttr) {
        log.info("writeProc()");
        String view = mServ.insertMovie(files, movie, session, rttr);

        return view;
    }

    @GetMapping("detail")
    public ModelAndView detail(Integer m_code){
        log.info("detail()");
        mv = mServ.getMovie(m_code);
        mv.setViewName("detail");
        return mv;
    }

    //수정 페이지로 전환
    @GetMapping("updateFrm")
    public ModelAndView updateFrm(Integer m_code){
        log.info("updateFrm()");
        mv = mServ.getMovie(m_code);
        mv.setViewName("updateFrm");
        return mv;
    }

    @PostMapping("updateProc")
    public String updateProc(@RequestPart List<MultipartFile> files,
                             MovieDto movie,
                             HttpSession session,
                             RedirectAttributes rttr){
        log.info("updateProc()");
        String view = mServ.movieUpdate(files, movie, session, rttr);

        return view;
    }

    //delete 메소드 추가
}
