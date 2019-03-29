package com.cristianperez.ubudrentalwithspring.presentation.web.global_handlers;

import com.cristianperez.ubudrentalwithspring.presentation.web.model.MovieSearchRequest;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class GlobalModelHandler {

    @ModelAttribute
    public void addAttributes(Model model) {
        model.addAttribute("movieSearchRequest", new MovieSearchRequest());
    }

}
