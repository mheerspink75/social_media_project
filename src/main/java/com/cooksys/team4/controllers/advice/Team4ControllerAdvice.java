package com.cooksys.team4.controllers.advice;

import javax.servlet.http.HttpServletRequest;

import com.cooksys.team4.dtos.ErrorDto;
import com.cooksys.team4.exceptions.BadRequestException;
import com.cooksys.team4.exceptions.NotAuthorizedException;
import com.cooksys.team4.exceptions.NotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice(basePackages = { "com.cooksys.team4.controllers" })
@ResponseBody
public class Team4ControllerAdvice {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BadRequestException.class)
    public ErrorDto handleBadRequestException(HttpServletRequest request, BadRequestException badRequestException) {
        return new ErrorDto(badRequestException.getMessage());
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public ErrorDto handleNotFoundException(HttpServletRequest request, NotFoundException notFoundException) {
        return new ErrorDto(notFoundException.getMessage());
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(NotAuthorizedException.class)
    public ErrorDto handleNotAuthorizedException(HttpServletRequest request,
            NotAuthorizedException notAuthorizedException) {
        return new ErrorDto(notAuthorizedException.getMessage());
    }

}
