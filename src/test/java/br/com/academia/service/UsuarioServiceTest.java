package br.com.academia.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.academia.dto.CadastroUsuarioDto;
import br.com.academia.enums.UserRoleEn;
import br.com.academia.exception.ResourceNotFoundException;
import br.com.academia.model.Usuario;
import br.com.academia.repository.UsuarioRepository;
import br.com.academia.strategy.CadastroUsuarioStrategy.ValidaCadastroUsuarioStrategy;
import br.com.academia.strategy.CadastroUsuarioStrategy.impl.PasswordhValidationImpl;


class UsuarioServiceTest {
	
	private static final String USUARIO_CADASTRADO_COM_SUCESSO = "Usuário cadastrado com sucesso!";

	private static final String USUARIO_NAO_ENCONTRADO = "Usuário não encontrado";

	private static final Integer INDEX = 0;
	
	private static final Integer ID = 5;

	private static final String NOME = "kaue";

	private static final String SOBRE_NOME = "sousa";

	private static final String EMAIL = "kauesousa@gmail.com";

	private static final String CPF = "12345678901";

	private static final String DATA_NASCIMENTO = "2003-02-11";

	private static final String TELEFONE = "85 999884338";

	private static final String GENERO = "MASCULINO";

	private static final String SENHA = "$2a$10$kF9gzP5XuIk7Kuj5X4i9fOxLSLA8.QYjLe5iSoN2TNIffrC9N2/SW";

	private static final String ROLE = "ADMIN";

	@Mock
	private UsuarioRepository usuarioRepository;
	
	@Mock
	private List<ValidaCadastroUsuarioStrategy> listValidateField;
	
	@Mock
    private PasswordhValidationImpl passwordValidation;
	
	@InjectMocks
	private UsuarioService usuarioService;

	private CadastroUsuarioDto cadastroDto;
	private Usuario usuario;

    @BeforeEach
    void setUp() {
    	MockitoAnnotations.openMocks(this);
    	startUser();
    }
    
    @Test
    void buscarCadastroPorId() {
    	when(usuarioRepository.findById(anyInt())).thenReturn(Optional.of(usuario));
    	
    	CadastroUsuarioDto response = usuarioService.buscarCadastroPorId(ID);
    	
    	assertNotNull(response);
    	
    	assertEquals(CadastroUsuarioDto.class, response.getClass());
    	assertEquals(ID, response.id());
    	assertEquals(NOME, response.nome());
    	assertEquals(SOBRE_NOME, response.sobreNome());
    	assertEquals(EMAIL, response.email());
    	assertEquals(CPF, response.cpf());
    	assertEquals(DATA_NASCIMENTO, response.dataNascimento());
    	assertEquals(TELEFONE, response.telefone());
    	assertEquals(GENERO, response.genero());
    	assertEquals(SENHA, response.senha());
    	assertEquals(ROLE, response.role());
    }
    
    @Test
    void deveLancarExceptionQuandoUsuarioNaoEncontrado() {
        when(usuarioRepository.findById(anyInt())).thenReturn(Optional.empty());
        
        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, 
            () -> usuarioService.buscarCadastroPorId(ID));
        
        assertEquals(USUARIO_NAO_ENCONTRADO, exception.getMessage());
    }

    @Test
    void buscarTodosCadastro() {
    	when(usuarioRepository.findAll()).thenReturn(List.of(usuario));
    	
    	List<CadastroUsuarioDto> response = usuarioService.buscarTodosCadastro();
    	
    	assertNotNull(response);
    	
    	assertFalse(response.isEmpty());
    	assertEquals(1, response.size());
    	assertEquals(CadastroUsuarioDto.class, response.get(INDEX).getClass());
    	assertEquals(ID, response.get(INDEX).id());
    }

    @Test
    void buscarTodosUsuario() {
    	Usuario usuario2 = new Usuario(2, "João", "Silva", "joao@gmail.com", "98765432100", "1990-01-01",
                "85 999883332", "MASCULINO", "password2", null, UserRoleEn.USER.name());
    	
    	when(usuarioRepository.findAll()).thenReturn(List.of(usuario, usuario2));
    	
    	List<CadastroUsuarioDto> response = usuarioService.buscarTodosUsuario();
    	
    	assertNotNull(response);
    	assertFalse(response.isEmpty());
    	
    	assertEquals(1, response.size());
    	assertEquals(CadastroUsuarioDto.class, response.get(INDEX).getClass());
    	assertEquals(usuario2.getNome(), response.get(INDEX).nome());
    }

    @Test
    void buscarUsarioPorEmail() {
    	when(usuarioRepository.findByEmail(anyString())).thenReturn(Optional.of(usuario));
    	
    	CadastroUsuarioDto response = usuarioService.buscarUsarioPorEmail(EMAIL);
    	
    	assertNotNull(response);
    	assertEquals(CadastroUsuarioDto.class, response.getClass());
    	
    	assertEquals(EMAIL, response.email());
    }
    
    @Test
    void lancarExcecaoQuandoNaoEncontrarEmail() {
    	when(usuarioRepository.findByEmail(anyString())).thenReturn(Optional.empty());
        
        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, 
            () -> usuarioService.buscarUsarioPorEmail(EMAIL));
        
        assertEquals(USUARIO_NAO_ENCONTRADO, exception.getMessage());
    }
    
    @Test
    void salvarCadastro() {
    	when(usuarioRepository.save(any(Usuario.class))).thenReturn(usuario);
    	
    	String response = usuarioService.salvarCadastro(cadastroDto);
    	
    	assertNotNull(response);
    	verify(listValidateField, times(1)).forEach(any());
    	assertEquals(USUARIO_CADASTRADO_COM_SUCESSO, response);
    }
    
    @Test
    void atualizarCadastro() {
    	when(usuarioRepository.findById(anyInt())).thenReturn(Optional.of(usuario));
    	when(usuarioRepository.save(any())).thenReturn(usuario);
    	
    	CadastroUsuarioDto response = usuarioService.atualizarCadastro(cadastroDto);
    	
    	assertNotNull(response);
    	assertEquals(CadastroUsuarioDto.class, response.getClass());
    	assertEquals(ID, response.id());
    }
    
    @Test
    void atualizarSenhaSeFornecida() {
        cadastroDto = new CadastroUsuarioDto(ID, NOME, SOBRE_NOME, EMAIL, CPF, DATA_NASCIMENTO,
                TELEFONE, GENERO, "newPassword", "newPassword", ROLE);
        
        when(usuarioRepository.findById(anyInt())).thenReturn(Optional.of(usuario));
        when(usuarioRepository.save(any())).thenReturn(usuario);
        
        CadastroUsuarioDto response = usuarioService.atualizarCadastro(cadastroDto);
        
        verify(passwordValidation, times(1)).validarCampos(any(CadastroUsuarioDto.class));
        assertNotNull(response);
        assertEquals(CadastroUsuarioDto.class, response.getClass());
        assertEquals(ID, response.id());
        assertNotEquals(SENHA, response.senha()); 
    }
    
    @Test
    void lancarExcecaoAoAtualizarUsuarioInexistente() {
        when(usuarioRepository.findById(anyInt())).thenReturn(Optional.empty());
        
        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, 
            () -> usuarioService.atualizarCadastro(cadastroDto));
        
        assertEquals(USUARIO_NAO_ENCONTRADO, exception.getMessage());
    }

    @Test
    void deletarUsuario() {
    	when(usuarioRepository.findById(anyInt())).thenReturn(Optional.of(usuario));
    	doNothing().when(usuarioRepository).delete(any(Usuario.class));
    	
    	assertDoesNotThrow(() -> usuarioService.deletarUsuario(ID));
    	
    	verify(usuarioRepository, times(1)).delete(usuario);
    }
    
    @Test
    void lancarExcecaoAoDeletarUsuarioInexistente() {
        when(usuarioRepository.findById(anyInt())).thenReturn(Optional.empty());
        
        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, 
            () -> usuarioService.deletarUsuario(ID));
        
        assertEquals(USUARIO_NAO_ENCONTRADO, exception.getMessage());
    }
    
    private void startUser() {
    	this.usuario = new Usuario(ID, NOME, SOBRE_NOME, EMAIL, CPF, DATA_NASCIMENTO,
    			TELEFONE, GENERO, SENHA, null, ROLE);
    	this.cadastroDto = new CadastroUsuarioDto(usuario);
    } 
}