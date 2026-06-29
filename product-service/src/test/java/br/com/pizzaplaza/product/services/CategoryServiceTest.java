package br.com.pizzaplaza.product.services;

import br.com.pizzaplaza.product.dtos.CategoryDto;
import br.com.pizzaplaza.product.entities.Category;
import br.com.pizzaplaza.product.repositories.CategoryRepository;
import jakarta.ws.rs.NotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class CategoryServiceTest {

    @Mock
    CategoryRepository categoryRepository;

    @InjectMocks
    CategoryService categoryService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void save_deve_persistir_categoria_e_retornar_dto_com_oid() {
        CategoryDto dto = new CategoryDto();
        dto.setDescription("Salgada");

        doAnswer(inv -> {
            Category cat = inv.getArgument(0);
            cat.beforePersist();
            return null;
        }).when(categoryRepository).save(any(Category.class));

        CategoryDto result = categoryService.save(dto);

        verify(categoryRepository).save(any(Category.class));
        assertNotNull(result.getOid());
        assertNotNull(result.getCreatedAt());
        assertEquals("Salgada", result.getDescription());
    }

    @Test
    void find_deve_retornar_dto_para_oid_valido() {
        Category category = new Category();
        category.setDescription("Doce");
        category.beforePersist();

        when(categoryRepository.findByOid(category.getOid())).thenReturn(category);

        CategoryDto result = categoryService.find(category.getOid());

        assertEquals("Doce", result.getDescription());
        assertEquals(category.getOid(), result.getOid());
    }

    @Test
    void find_deve_lancar_NotFoundException_para_oid_inexistente() {
        when(categoryRepository.findByOid("nao-existe")).thenReturn(null);

        assertThrows(NotFoundException.class, () -> categoryService.find("nao-existe"));
    }

    @Test
    void findAll_deve_retornar_lista_de_dtos() {
        Category c1 = new Category();
        c1.setDescription("Salgada");
        c1.beforePersist();

        Category c2 = new Category();
        c2.setDescription("Doce");
        c2.beforePersist();

        when(categoryRepository.findAll()).thenReturn(List.of(c1, c2));

        List<CategoryDto> result = categoryService.findAll();

        assertEquals(2, result.size());
        assertEquals("Salgada", result.get(0).getDescription());
        assertEquals("Doce", result.get(1).getDescription());
    }

    @Test
    void findAll_deve_retornar_lista_vazia_quando_repositorio_retorna_null() {
        when(categoryRepository.findAll()).thenReturn(null);

        List<CategoryDto> result = categoryService.findAll();

        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    void update_deve_lancar_excecao_quando_oid_e_nulo() {
        CategoryDto dto = new CategoryDto();
        dto.setDescription("Nova descrição");

        assertThrows(IllegalArgumentException.class, () -> categoryService.update(dto));
        verifyNoInteractions(categoryRepository);
    }

    @Test
    void update_deve_atualizar_descricao_da_categoria() {
        Category category = new Category();
        category.setDescription("Antiga");
        category.beforePersist();

        CategoryDto dto = new CategoryDto();
        dto.setOid(category.getOid());
        dto.setDescription("Nova");

        when(categoryRepository.findByOid(category.getOid())).thenReturn(category);
        when(categoryRepository.update(any(Category.class))).thenReturn(category);

        categoryService.update(dto);

        verify(categoryRepository).update(category);
        assertEquals("Nova", category.getDescription());
    }

    @Test
    void delete_deve_chamar_repositorio_com_a_entidade_correta() {
        Category category = new Category();
        category.setDescription("A deletar");
        category.beforePersist();

        when(categoryRepository.findByOid(category.getOid())).thenReturn(category);

        categoryService.delete(category.getOid());

        verify(categoryRepository).delete(category);
    }
}
