package org.example.controller;

import jakarta.validation.Valid;
import org.example.dto.ProductDTO;
import org.example.mapper.ProductMapper;
import org.example.model.Product;
import org.example.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/web/products")
public class ProductWebController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public String showProductList(Model model) {
        List<ProductDTO> productDTOs = productService.getAllProducts()
                .stream()
                .map(ProductMapper::toDTO)
                .collect(Collectors.toList());
        model.addAttribute("products", productDTOs);
        return "products/list";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("product", new ProductDTO());
        return "products/add";
    }

    @PostMapping("/add")
    public String addProduct(@Valid @ModelAttribute("product") ProductDTO productDTO,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "products/add";
        }

        Product product = ProductMapper.toEntity(productDTO);
        productService.saveProduct(product);

        redirectAttributes.addFlashAttribute("successMessage", "Thêm sản phẩm thành công!");
        return "redirect:/web/products";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Product product = productService.getProductById(id)
                .orElseThrow(() -> new IllegalArgumentException("ID sản phẩm không hợp lệ: " + id));
        ProductDTO dto = ProductMapper.toDTO(product);
        model.addAttribute("product", dto);
        return "products/edit";
    }

    @PostMapping("/edit/{id}")
    public String editProduct(@PathVariable Long id,
                              @Valid @ModelAttribute("product") ProductDTO productDTO,
                              BindingResult bindingResult,
                              RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "products/edit";
        }

        productDTO.setId(id); // đảm bảo ID được giữ nguyên
        Product product = ProductMapper.toEntity(productDTO);
        productService.saveProduct(product);

        redirectAttributes.addFlashAttribute("successMessage", "Cập nhật sản phẩm thành công!");
        return "redirect:/web/products";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id,
                                RedirectAttributes redirectAttributes) {
        productService.deleteProduct(id);
        redirectAttributes.addFlashAttribute("successMessage", "Xoá sản phẩm thành công!");
        return "redirect:/web/products";
    }
}
