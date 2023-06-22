package com.example.mysterymind.services.analyticActivity

import com.example.mysterymind.model.dao.NameDao
import com.example.mysterymind.model.entity.NameSignCompatibility
import com.example.mysterymind.model.dao.ZodiacDao
import com.example.mysterymind.model.entity.ZodiacSignCompatibility

class MaleInfoProcessor(private val nameDao: NameDao, private val zodiacDao: ZodiacDao) {
     fun getCompatibility(name: String): NameSignCompatibility? {
        return nameDao.findByName(name)
    }

     fun getZodiacSignCompatibility(zodiacSign: String): List<ZodiacSignCompatibility> {
        return zodiacDao.findZodiacSignCompatibility(zodiacSign)
    }

}
