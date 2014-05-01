package com.gwsystems.ncoredroid.utils;

import android.util.Log;

import com.gwsystems.ncoredroid.R;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by paalgyula on 2014.05.01..
 */
public abstract class CategoryIcons {
    private static Map<String, Integer> torrentCategoryMap = new HashMap<String, Integer>();

    static {
        // XXX
        torrentCategoryMap.put("xxx_imageset", R.drawable.ico_xxx_imageset);
        torrentCategoryMap.put("xxx_hd", R.drawable.ico_xxx_hd);
        torrentCategoryMap.put("xxx_xvid", R.drawable.ico_xxx_xvid);

        // XviD
        torrentCategoryMap.put("xvid", R.drawable.ico_xvid);
        torrentCategoryMap.put("xvid_hun", R.drawable.ico_xvid_hun);
        torrentCategoryMap.put("xvidser", R.drawable.ico_xvidser);
        torrentCategoryMap.put("xvidser_hun", R.drawable.ico_xvidser_hun);

        // HD
        torrentCategoryMap.put("hd", R.drawable.ico_hd);
        torrentCategoryMap.put("hd_hun", R.drawable.ico_hd_hun);
        torrentCategoryMap.put("hdser", R.drawable.ico_hdser);
        torrentCategoryMap.put("hdser_hun", R.drawable.ico_hdser_hun);

        // MP3
        torrentCategoryMap.put("mp3", R.drawable.ico_mp3);
        torrentCategoryMap.put("mp3_hun", R.drawable.ico_mp3_hun);

        // Lossless
        torrentCategoryMap.put("lossless", R.drawable.ico_lossless);
        torrentCategoryMap.put("lossless_hun", R.drawable.ico_lossless_hun);

        // Clip
        torrentCategoryMap.put("clip", R.drawable.ico_clip);

        // ISO
        torrentCategoryMap.put("iso", R.drawable.ico_iso);
        torrentCategoryMap.put("misc", R.drawable.ico_misc);
        torrentCategoryMap.put("mobil", R.drawable.ico_mobil);

        // DVD
        torrentCategoryMap.put("dvd", R.drawable.ico_dvd);
        torrentCategoryMap.put("dvd_hun", R.drawable.ico_dvd_hun);
        torrentCategoryMap.put("dvdser", R.drawable.ico_dvdser);
        torrentCategoryMap.put("dvdser_hun", R.drawable.ico_dvdser_hun);

        // DVD9
        torrentCategoryMap.put("dvd9", R.drawable.ico_dvd9);
        torrentCategoryMap.put("dvd9_hun", R.drawable.ico_dvd9_hun);

        // Game
        torrentCategoryMap.put("game_iso", R.drawable.ico_game_iso);
        torrentCategoryMap.put("game_rip", R.drawable.ico_game_iso);
        torrentCategoryMap.put("console", R.drawable.ico_console);

        // E-Book
        torrentCategoryMap.put("ebook", R.drawable.ico_ebook);
        torrentCategoryMap.put("ebook_hun", R.drawable.ico_ebook_hun);
    }

    public static int getIcon(String category) {
        try {
            return torrentCategoryMap.get(category);
        } catch (NullPointerException e) {
            Log.wtf("CategoryIcons", "A kategoriahoz nincs hozzarendelve kep: " + category);
            return R.drawable.ic_launcher;
        }
    }
}
