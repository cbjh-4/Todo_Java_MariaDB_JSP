package org.zerock.jdbcex.service;

import org.modelmapper.ModelMapper;
import org.zerock.jdbcex.dao.TodoDAO;
import org.zerock.jdbcex.domain.TodoVO;
import org.zerock.jdbcex.dto.TodoDTO;
import org.zerock.jdbcex.util.MapperUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public enum TodoService {
    INSTANCE;

    private TodoDAO dao;
    private ModelMapper modelMapper;

    TodoService(){
        dao = new TodoDAO();
        modelMapper = MapperUtil.INSTANCE.get();
    }

    public void register(TodoDTO dto) throws Exception {
        TodoVO vo = modelMapper.map(dto, TodoVO.class); //TodoDTO 타입을 TodoVO 타입으로 매핑시켜라.
        dao.insert(vo);
    }

    public List<TodoDTO> listAll() throws Exception {
        // DAO(Data Access Object)를 통해 데이터베이스에서 모든 할 일(Todo) 레코드를 조회합니다. 조회 결과는 TodoVO(Value Object) 리스트로 반환됩니다.
        List<TodoVO> voList = dao.selectAll();

        // 조회된 TodoVO 객체 리스트를 스트림으로 변환합니다.
        List<TodoDTO> dtoList = voList.stream()
                // 스트림의 각 요소(TodoVO 객체)를 TodoDTO 클래스의 인스턴스로 매핑합니다. 이 매핑 작업은 'modelMapper' 객체를 사용하여 수행됩니다.
                .map(vo -> modelMapper.map(vo, TodoDTO.class))
                // 매핑된 스트림을 리스트로 수집합니다. 최종 결과는 TodoDTO 객체의 리스트입니다.
                .collect(Collectors.toList());

        // DTO 리스트를 반환합니다.
        return dtoList;
    }

    public TodoDTO selectOne(Long tno) throws Exception {
        TodoVO todoVO = dao.selectOne(tno);
        TodoDTO todoDTO = modelMapper.map(todoVO, TodoDTO.class);
        return todoDTO;
    }

    public void update(TodoDTO dto) throws Exception {
        TodoVO vo = modelMapper.map(dto, TodoVO.class);
        if(dao.update(vo)){
            System.out.println("update success");
        }else {
            System.out.println("update fail");
        }
    }

    public void delete(Long tno) throws Exception {
        if(dao.delete(tno)){
            System.out.println("delete success");
        }else{
            System.out.println("delete fail");
        }
    }
}
