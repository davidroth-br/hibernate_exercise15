package dao;

import entities.Countries;
import java.util.List;

public interface CountriesDAO {
    List<Countries> getCountriesByRegion(Integer regionId);
    List<Object[]> getCountryCountByRegion();
}
