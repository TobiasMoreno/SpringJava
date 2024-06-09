package ar.edu.utn.frc.tup.lciii.services;

import ar.edu.utn.frc.tup.lciii.dtos.login.Credential;
import ar.edu.utn.frc.tup.lciii.dtos.login.CredentialV2;
import ar.edu.utn.frc.tup.lciii.models.Player;

public interface LoginService {
    Player login(Credential credential);
    Player login(CredentialV2 credential);
}
