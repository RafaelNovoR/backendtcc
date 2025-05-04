package com.ibgepopulation.tracker.helper;

import java.util.HashMap;
import java.util.Map;

public class CityMapper {
    public static final Map<String, String> cityIdMap = new HashMap<>();

    static {
        cityIdMap.put("Florianópolis", "4205407");
        cityIdMap.put("São José", "4216602");
        cityIdMap.put("Palhoça", "4211900");
        cityIdMap.put("Biguaçu", "4202305");
        cityIdMap.put("Santo Amaro da Imperatriz", "4216008");
        cityIdMap.put("Governador Celso Ramos", "4206009");
        cityIdMap.put("Antônio Carlos", "4201208");
        cityIdMap.put("Águas Mornas", "4200507");
        cityIdMap.put("São Pedro de Alcântara", "4217253");
    }

    public static String getCityId(String cityName) {
        return cityIdMap.get(cityName);
    }
}