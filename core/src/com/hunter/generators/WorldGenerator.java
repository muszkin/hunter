package com.hunter.generators;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.MapLayers;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;

public class WorldGenerator {

    public static int WIDTH = 1000;
    public static int HEIGHT = 1000;
    private TextureRegion[][] tileSet;
    private int[][] map = new int[WIDTH][HEIGHT];

    public WorldGenerator() {
        Texture texture = new Texture(Gdx.files.internal("pave.png"));
        this.tileSet = TextureRegion.split(texture,40,40);
    }
    public int[][] createMap() {
        for (int y = 0;y < HEIGHT;y++) {
            for (int x = 0; x < WIDTH; x++){
                this.map[x][y] = (int) Math.random() * 10;
            }
        }
        return this.map;
    }

    public TiledMap generateMap() {
        TiledMap map = new TiledMap();
        MapLayers layers = map.getLayers();
        for (int l = 0;l < 10;l ++) {
            TiledMapTileLayer layer = new TiledMapTileLayer(100,100,40,40);
            for (int row = 0; row < 100; row++) {
                for (int col = 0; col < 100; col++) {
                    TiledMapTile mapTile;
                    if (Math.random() * 10 < 5) {
                        mapTile = new StaticTiledMapTile(tileSet[0][0]);
                    } else {
                        mapTile = new StaticTiledMapTile(tileSet[2][2]);
                    }
                    TiledMapTileLayer.Cell cell = new TiledMapTileLayer.Cell();
                    cell.setTile(mapTile);
                    layer.setCell(row,col,cell);
                }
            }
            layers.add(layer);
        }
        return map;
    }

}
