package br.com.pizzaplaza.product.services;

import br.com.pizzaplaza.product.dtos.CategoryDto;
import br.com.pizzaplaza.product.dtos.FlavorDto;
import br.com.pizzaplaza.product.entities.Category;
import br.com.pizzaplaza.product.entities.Flavor;
import br.com.pizzaplaza.product.repositories.FlavorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class FlavorServiceTest {

    @Mock
    FlavorRepository flavorRepository;

    @Mock
    CategoryService categoryService;

    @InjectMocks
    FlavorService flavorService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    private CategoryDto makeCategoryDto(String oid) {
        CategoryDto dto = new CategoryDto();
        dto.setOid(oid);
        dto.setDescription("Categoria " + oid);
        return dto;
    }

    private FlavorDto makeFlavorDto(String categoryOid) {
        FlavorDto dto = new FlavorDto();
        dto.setName("Margherita");
        dto.setPrice(new BigDecimal("35.00"));
        dto.setDescription("Sabor clássico");
        dto.setCategories(List.of(makeCategoryDto(categoryOid)));
        return dto;
    }

    @Test
    void save_deve_lancar_excecao_quando_categoria_sem_oid() {
        FlavorDto dto = makeFlavorDto(null);

        assertThrows(IllegalArgumentException.class, () -> flavorService.save(dto));
        verifyNoInteractions(flavorRepository);
    }

    @Test
    void save_deve_lancar_excecao_quando_lista_de_categorias_tem_oid_vazio() {
        FlavorDto dto = makeFlavorDto("");

        assertThrows(IllegalArgumentException.class, () -> flavorService.save(dto));
    }

    @Test
    void save_deve_persistir_sabor_e_retornar_dto_com_oid() {
        FlavorDto dto = makeFlavorDto("cat-uuid-1");

        Category category = new Category();
        category.setDescription("Categoria");
        category.beforePersist();

        when(categoryService.findByFlavorCategoryList(any())).thenReturn(List.of(category));
        doAnswer(inv -> {
            Flavor flavor = inv.getArgument(0);
            flavor.beforePersist();
            return null;
        }).when(flavorRepository).save(any(Flavor.class));

        FlavorDto result = flavorService.save(dto);

        verify(flavorRepository).save(any(Flavor.class));
        assertNotNull(result.getOid());
        assertNotNull(result.getCreatedAt());
        assertEquals("Margherita", result.getName());
        assertEquals(new BigDecimal("35.00"), result.getPrice());
    }

    @Test
    void findAll_deve_retornar_lista_vazia_quando_repositorio_retorna_lista_vazia() {
        when(flavorRepository.findAll(any())).thenReturn(List.of());

        List<FlavorDto> result = flavorService.findAll(null);

        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    void findAll_deve_retornar_lista_vazia_quando_repositorio_retorna_null() {
        when(flavorRepository.findAll(any())).thenReturn(null);

        List<FlavorDto> result = flavorService.findAll(null);

        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    void update_deve_lancar_excecao_quando_oid_e_nulo() {
        FlavorDto dto = makeFlavorDto("cat-1");

        assertThrows(IllegalArgumentException.class, () -> flavorService.update(dto));
        verifyNoInteractions(flavorRepository);
    }

    @Test
    void update_deve_lancar_excecao_quando_categoria_sem_oid() {
        FlavorDto dto = makeFlavorDto(null);
        dto.setOid("flavor-uuid");

        assertThrows(IllegalArgumentException.class, () -> flavorService.update(dto));
    }

    @Test
    void delete_deve_chamar_repositorio_com_entidade_correta() {
        Flavor flavor = new Flavor();
        flavor.setName("Calabresa");
        flavor.beforePersist();

        when(flavorRepository.findByOid(flavor.getOid())).thenReturn(flavor);

        flavorService.delete(flavor.getOid());

        verify(flavorRepository).delete(flavor);
    }
}
