package com.icia.movieinfo.dao;

import com.icia.movieinfo.dto.MovieDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface MovieDao {
    List<MovieDto> getMovieList(Map<String, Integer> map);

    void movieInsert(MovieDto movie);

    @Select("SELECT * FROM movietbl WHERE m_code=#{m_code}")
    MovieDto movieSelect(Integer m_code);

    void movieUpdate(MovieDto movie);

    @Select("SELECT count(*) FROM movietbl")
    int cntMovie();
    
    //delete 추가
}
