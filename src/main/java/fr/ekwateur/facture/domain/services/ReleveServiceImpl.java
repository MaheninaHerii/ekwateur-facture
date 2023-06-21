package fr.ekwateur.facture.domain.services;

import fr.ekwateur.facture.adapters.persistences.ReleveDao;
import fr.ekwateur.facture.domain.models.persistence.ReleveEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Service
public class ReleveServiceImpl {

    private final ReleveDao releveDao;

    public ReleveServiceImpl(ReleveDao releveDao) {
        this.releveDao = releveDao;
    }

    public List<ReleveEntity> getReleves(Date periode, Long idClient) {
        LocalDate dateConso = periode.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate debutMois = dateConso.withDayOfMonth(1);
        LocalDate finMois = debutMois.withDayOfMonth(debutMois.lengthOfMonth());

        Date dateDebut = convertToDate(debutMois);
        Date dateFin = convertToDate(finMois);

        return releveDao.findByPeriodeBetweenAndIdReleveIdClient(dateDebut, dateFin, idClient);
    }

    private Date convertToDate(LocalDate localDate) {
        return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }
}
