
package id.ac.ub.filkom.contoh.controller;

import id.ac.ub.filkom.contoh.service.AnggotaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AnggotaController {

    private final AnggotaService anggotaService;

    public AnggotaController(AnggotaService anggotaService) {
        this.anggotaService = anggotaService;
    }

    // Menampilkan daftar anggota
    @GetMapping("/anggota")
    public String lihatAnggota(Model model) {
        model.addAttribute(
            "daftarAnggota",
            anggotaService.lihatSemuaAnggota()
        );
        return "anggota";
    }

    // Memproses form tambah anggota
    @PostMapping("/anggota")
    public String tambahAnggota(
            @RequestParam String idAnggota,
            @RequestParam String nama,
            @RequestParam int batasPinjam,
            Model model) {

        try {
            anggotaService.tambahAnggota(
                idAnggota.trim(),
                nama.trim(),
                batasPinjam
            );
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute(
                "daftarAnggota",
                anggotaService.lihatSemuaAnggota()
            );
            return "anggota";
        }

        return "redirect:/anggota";
    }
}