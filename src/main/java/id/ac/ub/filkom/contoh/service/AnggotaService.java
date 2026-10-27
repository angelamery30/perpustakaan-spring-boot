
package id.ac.ub.filkom.contoh.service;

import id.ac.ub.filkom.contoh.entity.Anggota;
import id.ac.ub.filkom.contoh.repository.AnggotaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnggotaService {

    private final AnggotaRepository anggotaRepository;

    public AnggotaService(AnggotaRepository anggotaRepository) {
        this.anggotaRepository = anggotaRepository;
    }

    public List<Anggota> lihatSemuaAnggota() {
        return anggotaRepository.findAll();
    }

    public void tambahAnggota(String idAnggota, String nama, int batasPinjam) {
        if (idAnggota == null || idAnggota.isBlank()
                || nama == null || nama.isBlank()) {
            throw new IllegalArgumentException("ID dan nama wajib diisi.");
        }

        if (anggotaRepository.existsById(idAnggota)) {
            throw new IllegalArgumentException(
                "ID anggota sudah dipakai: " + idAnggota
            );
        }

        anggotaRepository.save(
            new Anggota(idAnggota, nama, batasPinjam)
        );
    }
}