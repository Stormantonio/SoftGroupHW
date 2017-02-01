package javaHW2.songStorage;

/**
 * Created by Anton on 01.02.2017.
 */
class Song {
    private String songName;
    private String author;
    private int rate;

    Song(String songName, String author, int rate) {
        this.songName = songName;
        this.author = author;
        this.rate = rate;
    }

    String getSongName() {
        return songName;
    }

    String getAuthor() {
        return author;
    }

    int getRate() {
        return rate;
    }
}
