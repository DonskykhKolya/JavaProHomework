package ua.kiev.prog.dao;

import ua.kiev.prog.entity.Address;


public interface AddressDAO {

    void add(Address newAddres);
    Address getAddress(int userId);
    void delete(int id);
}
