package GGTStore.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import GGTStore.dao.AccountDAO;
import GGTStore.dao.AuthorityDAO;
import GGTStore.dao.RoleDAO;
import GGTStore.dto.AccountDto;
import GGTStore.entity.Account;
import GGTStore.service.AccountService;

@Controller
@RequestMapping("/account")
public class AccountController {
    @Autowired
    HttpSession session;
    @Autowired
    AccountService accountService;
    @Autowired
    AccountDAO accountDAO;
    @Autowired
    AuthorityDAO authorityDAO;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    RoleDAO roleDAO;

    @GetMapping("detail/{username}")
    public String detail(Model model, @PathVariable("username") String username) {
        Account account = accountService.findByUsernameContaining(username);
        model.addAttribute("account", account);
        return "account/updateProfile";
    }

    @GetMapping("edit/{username}")
    public ModelAndView edit(ModelMap model, @PathVariable("username") String username) {
        Optional<Account> opt = accountService.findByUsername(username);
        Account dto = new Account();
        if (opt.isPresent()) {
            Account entity = opt.get();
            BeanUtils.copyProperties(entity, dto);
            dto.setIsEdit(true); // thiet lap o che do chinh sua thong tin
            dto.setPassword("");
            model.addAttribute("account", dto);
            return new ModelAndView("account/updateProfile", model);
        }
        model.addAttribute("message", "Update is not existed");
        return new ModelAndView("forward:/account", model);
    }

    @PostMapping("saveOrUpdate")
    public String saveOrUpdate(ModelMap model, @Valid @ModelAttribute("account") AccountDto dto, BindingResult result) {
        // if (result.hasErrors()) {
        // System.out.println("loi");
        // return "account/updateProfile";
        // }
        Account entity = new Account();
        BeanUtils.copyProperties(dto, entity);
        accountService.save(entity);
        model.addAttribute("message", "Cập nhật thành công!");
        System.out.println("ok");
        return "security/login";
    }

    // save file
    public void upload(MultipartFile file, String dir) throws IOException {
        Path path = Paths.get(dir);
        InputStream inputStream = file.getInputStream();
        Files.copy(inputStream, path.resolve(file.getOriginalFilename()), StandardCopyOption.REPLACE_EXISTING);
    }

    // @PostMapping("saveOrUpdate")
    // public String saveOrUpdate(ModelMap model, @Valid @ModelAttribute("account")
    // Account dto, BindingResult result,
    // @RequestParam("image") MultipartFile image, Principal principal) throws
    // IOException {

    // if (result.hasErrors()) {
    // return "account/updateProfile";
    // }
    // Account entity = accountDAO.findByEmail(principal.getName());
    // if (!image.getOriginalFilename().equals("")) {
    // upload(image, "uploads/customers");
    // entity.setImage(image.getOriginalFilename());
    // }
    // // Account entity = new Account();
    // BeanUtils.copyProperties(dto, entity);
    // accountDAO.save(entity);

    // model.addAttribute("message", "Update Success!");
    // return "security/login";
    // }

}