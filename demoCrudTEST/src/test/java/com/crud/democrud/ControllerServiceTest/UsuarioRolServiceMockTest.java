package com.crud.democrud.ControllerServiceTest;

import com.crud.democrud.repositories.UsuarioRolRepository;
import com.crud.democrud.services.UsuarioRolServiceImpl;
import com.crud.democrud.services.UsuarioService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@SpringBootTest
public class UsuarioRolServiceMockTest {

  @MockBean
  UsuarioRolRepository usuarioRolRepository;

  @Autowired
  UsuarioRolServiceImpl usuarioRolService;

  @Test
  public void testUsuarioRolMock(){
    when(usuarioRolRepository.findAll()).thenReturn(new ArrayList<>());
    assertThat(usuarioRolService.getAll()).isEmpty();
    verify(usuarioRolRepository).findAll();
  }

}
