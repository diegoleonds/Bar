package com.example.bar;

import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.bar.model.Bar;
import com.example.bar.model.BarDAO;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();

        assertEquals("com.example.bar", appContext.getPackageName());
    }

    @Test
    public Context pegaContext(){

        return InstrumentationRegistry.getInstrumentation().getTargetContext();
    }

    private List<Bar> baresTeste(){


        ArrayList<Bar> bar = new ArrayList<Bar>();

        //bar.add(new Bar("Bar do Juka", "rua 7  de setembro, bairro Itoupavazinha, Blumenau, SC,"));
       //bar.add(new Bar("Bar do Jose", "rua Sem volta, bairro Moinho, Luiz Alves, ES"));

        return bar;
    }
    @Test
    public void testeInsertBar() {

        ArrayList<Bar> bars = new ArrayList<Bar>(baresTeste());

        BarDAO barDAO = new BarDAO(pegaContext());

        for( Bar bar : bars) {
            barDAO.inserirBar(bar);
        }

        ArrayList<Bar> meDAOsBares =
                new ArrayList<Bar>(barDAO.meDAOsBar());

        int index = 1;

        Assert.assertEquals(bars.get(index).getNome(), meDAOsBares.get(index).getNome());

    }
    

}



