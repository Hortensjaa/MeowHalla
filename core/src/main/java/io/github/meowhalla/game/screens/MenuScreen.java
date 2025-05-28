package io.github.meowhalla.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import io.github.meowhalla.Main;
import io.github.meowhalla.player.PlayerWeapons;
import io.github.meowhalla.projectiles.Weapon;

public class MenuScreen implements Screen {
    private final Stage stage;
    private final Skin skin;
    private final BitmapFont font;
    private TextButton startButton;

    private Weapon activeWeapon;

    public MenuScreen() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        skin = new Skin(Gdx.files.internal("skin/uiskin.json"));
        font = fontGenerator();
    }

    private BitmapFont fontGenerator() {
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("skin/Jacquard12-Regular.ttf"));
        FreeTypeFontParameter parameter = new FreeTypeFontParameter();
        parameter.size = 120;
        BitmapFont font = generator.generateFont(parameter);
        generator.dispose();
        return font;
    }

    private void addWeaponButtonToTable(Table table, Weapon weapon) {
        TextButton btn = new TextButton(weapon.getWeaponContext().name(), skin);
        btn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (activeWeapon != weapon) {
                    activeWeapon = weapon;
                    updateWeaponButtonStates(table);
                    updateStartButtonState();
                } else {
                    activeWeapon = null;
                    updateWeaponButtonStates(table);
                    updateStartButtonState();
                }
            }
        });

        Label tooltipLabel = new Label(weapon.getWeaponContext().description(), skin);
        Tooltip<Label> tooltip = new Tooltip<>(tooltipLabel);
        btn.addListener(tooltip);

        table.row().pad(10);
        table.add(btn).width(300).height(60).center();
    }

    private void updateWeaponButtonStates(Table table) {
        for (Actor actor : table.getChildren()) {
            if (actor instanceof TextButton weaponButton) {
                if (weaponButton.getText().toString().equals(activeWeapon != null ? activeWeapon.getWeaponContext().name() : "")) {
                    weaponButton.setChecked(true);
                } else {
                    weaponButton.setChecked(false);
                }
            }
        }
    }

    private void updateStartButtonState() {
        startButton.setDisabled(activeWeapon == null);
    }

    @Override
    public void show() {
        Table table = new Table();
        table.setFillParent(true);
        stage.addActor(table);

        // Title
        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = font;
        Label title = new Label("Meowhalla", labelStyle);
        table.add(title).padBottom(40).colspan(1).center();
        table.row();

        // Weapon buttons
        addWeaponButtonToTable(table, PlayerWeapons.PHALANX_OF_LIGHT.data);
        addWeaponButtonToTable(table, PlayerWeapons.RAY_OF_SUN.data);
        addWeaponButtonToTable(table, PlayerWeapons.LUNAR_ECLIPSE.data);

        // Start button
        startButton = new TextButton("Start", skin);
        startButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (activeWeapon != null) {
                    Main.getInstance().setScreen(new WolfBossScreen(activeWeapon));
                }
            }
        });
        startButton.setDisabled(true);
        table.row().padTop(30);
        table.add(startButton).width(200).height(40).center();
    }


    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.1f, 0.1f, 0.1f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act(delta);
        stage.draw();
    }

    @Override public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override public void pause() {}
    @Override public void resume() {}
    @Override public void hide() {}

    @Override
    public void dispose() {
        stage.dispose();
        skin.dispose();
        font.dispose();
    }
}


