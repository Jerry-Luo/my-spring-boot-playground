package com.jerry.validation;

import com.jerry.validation.model.User;
import org.springframework.boot.context.properties.bind.validation.ValidationErrors;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
public class ValidationTestController {

    @PostMapping("/test/validation")
    public String testValidation (@RequestBody @Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<ObjectError> errors = bindingResult.getAllErrors();
            errors.forEach(e -> System.err.println(e.getObjectName()+" --> " + e.getDefaultMessage()));
        }
        return "success";
    }
}
