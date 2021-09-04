package com.example.mmall.Exception;

import com.example.mmall.enums.ResultEnum;

public class MyException extends RuntimeException{
public MyException(String error){
    super(error);

}
    public MyException(ResultEnum resultEnum){
        super(resultEnum.getMsg());

    }
}
