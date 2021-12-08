package org.zerock.w3.service;

import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.zerock.w3.dao.TodoDAO;
import org.zerock.w3.dto.TodoDTO;
import org.zerock.w3.util.MapperUtil;

import java.util.List;
import java.util.stream.Collectors;

@Log4j2
public enum TodoService {

    INSTANCE;

    private ModelMapper modelMapper;
    private TodoDAO todoDAO;

    TodoService() {
        modelMapper = MapperUtil.INSTANCE.get();
        todoDAO = TodoDAO.INSTANCE;
    }

    public List<TodoDTO> getAll()throws Exception{

        return todoDAO.getAll().stream()
                .map(vo -> modelMapper.map(vo,TodoDTO.class))
                .collect(Collectors.toList());

    }



}