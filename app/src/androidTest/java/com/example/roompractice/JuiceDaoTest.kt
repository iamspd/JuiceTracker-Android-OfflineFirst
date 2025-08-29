package com.example.roompractice

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.roompractice.data.dao.JuiceDao
import com.example.roompractice.data.database.JuiceDatabase
import com.example.roompractice.data.entity.JuiceEntity
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class JuiceDaoTest {
    private lateinit var juiceDao: JuiceDao
    private lateinit var juiceDatabase: JuiceDatabase

    private var juice1 = JuiceEntity(
        id = 1L,
        name = "Juice 1",
        description = "Description 1",
        color = "Red",
        rating = 4.5f
    )
    private var juice2 = JuiceEntity(
        id = 2L,
        name = "Juice 2",
        description = "Description 2",
        color = "Green",
        rating = 3.5f
    )

    private suspend fun addOneJuiceToDb() {
        juiceDao.insertJuice(juice1)
    }

    private suspend fun addTwoJuicesToDb() {
        juiceDao.insertJuice(juice1)
        juiceDao.insertJuice(juice2)
    }

    @Before
    fun createDb() {
        val context: Context = ApplicationProvider.getApplicationContext()
        juiceDatabase = Room.inMemoryDatabaseBuilder(
            context, JuiceDatabase::class.java
        ).allowMainThreadQueries().build()

        juiceDao = juiceDatabase.juiceDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        juiceDatabase.close()
    }

    @Test
    fun juiceDao_getJuiceByIdFromDb_retrievesCorrectJuice() = runTest {
        addTwoJuicesToDb()

        val retrievedJuice = juiceDao.getJuiceById(1L).first()
        assertEquals(retrievedJuice, juice1)
    }

    @Test
    fun juiceDao_deleteJuicesInDb_verifyData() = runTest {
        addTwoJuicesToDb()

        var allJuices = juiceDao.getJuiceStream().first()
        assertEquals(allJuices.size, 2)

        juiceDao.deleteJuice(juice1)
        juiceDao.deleteJuice(juice2)
        allJuices = juiceDao.getJuiceStream().first()
        assertTrue("The database is empty.", allJuices.isEmpty())
    }

    @Test
    fun juiceDao_updateJuiceInDb_verifyUpdatedJuice() = runTest {
        addOneJuiceToDb()
        val updatedJuice = juice1.copy(name = "Apple Juice")
        juiceDao.updateJuice(updatedJuice)
        val allJuices = juiceDao.getJuiceStream().first()

        assertEquals(allJuices[0].name, updatedJuice.name)
    }


    @Test
    fun juiceDao_insertOneJuiceToDb_retrieveAddedJuice() = runTest {
        addOneJuiceToDb()
        val allJuices = juiceDao.getJuiceStream().first()
        assertEquals(allJuices[0], juice1)
    }
}