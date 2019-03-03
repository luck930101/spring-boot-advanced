package com.yuboz.springbootadvanced.web;

import com.yuboz.springbootadvanced.domain.Book;
import com.yuboz.springbootadvanced.exception.BookNotFoundException;
import com.yuboz.springbootadvanced.service.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

@Controller
@RequestMapping("/books")
public class BookController {

    private final Logger logger = LoggerFactory.getLogger(BookController.class);

    @Autowired
    private BookService bookService;

    @GetMapping("/{id}")
    public String getBook(@PathVariable Long id, Model model){

        Book book = bookService.getBookById(id);

        if(book == null){
            throw new BookNotFoundException("Book info does not exist");
        }

        model.addAttribute(book);

        return "book";
    }
//
//    @ExceptionHandler({Exception.class, BookNotFoundException.class, SQLException.class})
//    public ModelAndView handleException(HttpServletRequest reuest, Exception e) throws Exception {
//
//        logger.error("Request URL : {}, Exception : {}", reuest.getRequestURI(),e.getMessage());
//
//        if(AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class)!=null){
//            throw e;
//        }
//
//        ModelAndView mav = new ModelAndView();
//        mav.addObject("url", reuest.getRequestURL());
//        mav.addObject("exception",e);
//        mav.setViewName("error/error");
//
//        return mav;
//
//
//    }

}
