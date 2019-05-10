package ma.pfa.webapp.dao;

import ma.pfa.webapp.model.Client;

public interface IClientDao extends ICrudGenericDao<Client> {

	Client getClientByUserId(int id);
}
