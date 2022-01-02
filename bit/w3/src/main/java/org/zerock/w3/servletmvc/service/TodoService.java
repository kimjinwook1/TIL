package org.zerock.w3.servletmvc.service;

import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.zerock.w3.servletmvc.dao.TodoDAO;
import org.zerock.w3.servletmvc.domain.TodoVO;
import org.zerock.w3.servletmvc.dto.TodoDTO;
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

    public List<TodoDTO> getAll() throws Exception {

        return todoDAO.getAll().stream()
                .map(vo -> modelMapper.map(vo, TodoDTO.class))
                .collect(Collectors.toList());
    }

    public TodoVO saveOne(TodoDTO todoDTO) throws Exception {

        TodoVO vo = modelMapper.map(todoDTO, TodoVO.class);
        return vo;
    }

    //강사님 코드
    public void register(TodoDTO dto) throws Exception {
        log.info("dto: " + dto);
        TodoVO vo = modelMapper.map(dto, TodoVO.class);
        log.info("vo: " + vo);

        todoDAO.insert(vo);
    }

    public TodoDTO read(Long tno) throws Exception {

        TodoDTO todoDTO = modelMapper.map(todoDAO.selectOne(tno), TodoDTO.class);

        return todoDTO;
    }
    public long readRecentTno() throws Exception {

        return todoDAO.selectOneByRecentValue();
    }

    public void remove(Long tno) throws Exception {

        todoDAO.deleteOne(tno);

    }

    public void update(TodoDTO dto) throws Exception {

        TodoVO todoVO = modelMapper.map(dto, TodoVO.class);

        todoDAO.modify(todoVO);

    }

    public void updateWriter(String writer, int writerId) throws Exception {

        TodoDAO.INSTANCE.updateWriter(writer, writerId);

    }

    public void removeByUno(int writerId) throws Exception{

        TodoDAO.INSTANCE.deleteOneByWriterId(writerId);
    }
}