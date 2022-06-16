package ufjf.br.beatriz.exemplo05;

import android.content.Context;
import android.content.SharedPreferences;

public class EstatisticasRepository {
    private Context context;
    private SharedPreferences preferences;
    private static final String PREFERENCES_NAME ="br.ufjf.dcc196.beatriz.ufjf-dcc196-2022-1-atv5-beatrizcunharo;";

    public EstatisticasRepository(Context context)
    {
        this.context = context;
        preferences = context.getSharedPreferences(PREFERENCES_NAME,Context.MODE_PRIVATE);
    }

    public Integer getValue(String key)
    {
        return preferences.getInt(key,0);
    }

    public void setValue(Integer points, String key)
    {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(key, points);
        editor.apply();
    }

    public void incValue(String key) {
        setValue(getValue(key)+1, key);
    }
    public void decValue(String key) {
        setValue(getValue(key)-1, key);
    }

    public String getNome(String key) {
        return preferences.getString(key,"Bem vindo!");
    }

    public void setNome(String nome, String key) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(key, nome);
        editor.apply();
    }

    public  SharedPreferences getPreferences()
    {
        return preferences;
    }
}