package com.vacinacerta.usecase;

public interface IUseCase <Input, Output> {
    Output execute(Input input);
}
