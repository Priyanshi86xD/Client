package com.cheatbreaker.client.config;

import com.cheatbreaker.bridge.client.settings.GameSettingsBridge;
import com.cheatbreaker.bridge.client.settings.KeyBindingBridge;
import com.cheatbreaker.bridge.ref.Ref;
import com.cheatbreaker.client.config.types.PinnedServer;
import com.cheatbreaker.client.config.types.UnrecommendedServer;
import com.cheatbreaker.main.CheatBreaker;
import com.cheatbreaker.client.ui.element.type.ColorPickerColorElement;
import com.cheatbreaker.client.util.dash.DashUtil;
import com.cheatbreaker.client.util.voicechat.VoiceChatManager;
import lombok.Getter;
import org.apache.commons.lang3.ArrayUtils;

import java.util.ArrayList;
import java.util.List;

public class GlobalSettings {
    public Setting followMinecraftScale;
    public Setting miscellaneousOptionsLabel;
    public Setting mainMenuTopGradient;
    public Setting mainMenuLightGradient;
    public KeyBindingBridge openMenu;
    public KeyBindingBridge openVoiceMenu;
    public KeyBindingBridge pushToTalk;
    public KeyBindingBridge dragLook;
    public KeyBindingBridge hideNames;
    public final List<Setting> settingsList = new ArrayList<>();
    public List<PinnedServer> pinnedServers;
    public List<UnrecommendedServer> unrecommendedServers;
    public boolean isDebug = true;
    public String crashReportURL = "https://cheatbreaker.com/crash-report-upload";
    public String debugUploadURL = "https://cheatbreaker.com/debug-upload";
    public String cosmeticApiURL = "https://cheatbreaker.com/api/cosmetic/";
    public String cosmeticAllApiURL = "https://cheatbreaker.com/api/cosmetic/all";
    public String mojangStatusURL = "https://status.mojang.com/check";
    public int reconnectTime = 60;
    public boolean IIIlllIIIllIllIlIIIIIIlII = true;

    public Setting theme;
    private Setting audioSettingsLabel;
    public Setting microphone;
    public Setting radioVolume;
    public Setting microphoneVolume;
    public Setting speakerVolume;
    public Setting attenuation;
    public Setting pinRadio;
    public Setting muteCheatBreakerSounds;
    private Setting fpsBoostLabel;
    public Setting enableFpsBoost;
    public Setting slowChunkLoading;
    public Setting fullBright;
    public Setting enchantmentGlint;
    private Setting teamViewLabel;
    public Setting enableTeamView;
    public Setting showDistance;
    public Setting showOffScreenMarker;
    private Setting generalSettingsLabel;
    public Setting guiBlur;
    public Setting worldTime;
    public Setting lookView;
    public Setting snapModules;
    private Setting renderSettingsLabel;
    public Setting showHudInDebug;
    public Setting showChatBackground;
    public Setting shinyPots;
    public Setting showPotionInfo;
    public Setting clearGlass;
    public Setting redString;
    public Setting transparentBackground;
    @Getter private Setting crosshairSettingsLabel;
    public Setting customCrosshair;
    public Setting crosshairOutline;
    public Setting crosshairColor;
    public Setting crosshairThickness;
    public Setting crosshairSize;
    public Setting crosshairGap;
    private Setting colorOptionsLabel;
    public Setting defaultColor;
    // what is this xd
    // Nox: apparently, CheatBreaker code, Decencies.
    public List<ColorPickerColorElement> favouriteColors = new ArrayList<>();
    public List<ColorPickerColorElement> IlIIlIIlIllIIIIllIIllIlIl = new ArrayList<>();

//    public boolean lIIIIlIIllIIlIIlIIIlIIllI() {
//        return (Boolean)this.IlIIlIIIIlIIIIllllIIlIllI.getValue() == false || (Boolean)this.lIllIllIlIIllIllIlIlIIlIl.getValue() != false;
//    }

    public boolean isFavouriteColor(int n) {
        for (ColorPickerColorElement colorPickerColorElement : this.favouriteColors) {
            if (colorPickerColorElement.color != n) continue;
            return true;
        }
        return false;
    }

    public void removeFavouriteColor(int n) {
        this.favouriteColors.removeIf(colorPickerColorElement -> colorPickerColorElement.color == n);
    }

    public GlobalSettings() {
        this.theme = new Setting(this.settingsList, "Theme").setValue("Light").acceptedValues("Light", "Dark");

        String[] audioDevices = CheatBreaker.getInstance().getAudioDeviceList();

        this.audioSettingsLabel = (new Setting(this.settingsList, "label")).setValue("Audio Settings");
        if (audioDevices.length > 0) {
            this.microphone = (new Setting(this.settingsList, "Microphone")).setValue(audioDevices[0]).acceptedValues(audioDevices).onChange((var0) -> {
                try {
                    System.out.println("[CB] Updated audio device!");
                } catch (UnsatisfiedLinkError var2) {
                    System.err.println("[CB] CBAgent.dll is not attached.");
                }
            });
        } else {
            this.microphone = (new Setting(this.settingsList, "Microphone")).setValue("Unknown").acceptedValues("Unknown");
        }

        this.muteCheatBreakerSounds = (new Setting(this.settingsList, "Mute CheatBreaker sounds")).setValue(false);
        this.pinRadio = (new Setting(this.settingsList, "Pin Radio Player")).setValue(false);
        this.radioVolume = (new Setting(this.settingsList, "Radio Volume")).onChange((var0) -> {
            if (DashUtil.isPlayerNotNull()) {
                DashUtil.getDashPlayer().setFloatControlValue((float)var0);
            }
        }).setValue(85f).setMinMax(55f, 100f);
        this.microphoneVolume = (new Setting(this.settingsList, "Microphone Volume")).onChange(volume -> {
            if (VoiceChatManager.isExistent()) {
                CheatBreaker.getInstance().getVoiceChatManager().getRecorder().microphoneVolume = ((Integer) volume) / 100f;
                if (CheatBreaker.getInstance().getModuleManager() != null) {
                    CheatBreaker.getInstance().getModuleManager().voiceChat.checkMicVolume = true;
                }
            }
        }).setValue(50).setMinMax(0, 100);
        this.speakerVolume = (new Setting(this.settingsList, "Speaker Volume")).onChange((var0) -> {
            try {
                float var1 = (float)20000 / (float)(20000 - Math.max(0, Math.min(19500, (int) var0 * 195)));
                //Message.m(var1);
            } catch (UnsatisfiedLinkError var2) {
                System.err.println("[CB] CBAgent.dll is not attached.");
            }
        }).setValue(85).setMinMax(0, 100);
        this.attenuation = (new Setting(this.settingsList, "Attenuation")).setValue(0).setMinMax(0, 100);

        this.fpsBoostLabel = new Setting(this.settingsList, "label").setValue("FPS Boost");
        this.enableFpsBoost = new Setting(this.settingsList, "Enable FPS Boost").setValue(true);
        this.slowChunkLoading = new Setting(this.settingsList, "Slow chunk loading (%)").setMinMax(5, 100).setValue(30);
        this.fullBright = new Setting(this.settingsList, "Fullbright").setValue(true);
        this.enchantmentGlint = new Setting(this.settingsList, "Enchantment Glint").setValue(true);

        this.teamViewLabel = new Setting(this.settingsList, "label").setValue("Team View Settings");
        this.enableTeamView = new Setting(this.settingsList, "Enable Team View").setValue(true);
        this.showOffScreenMarker = new Setting(this.settingsList, "Show off-screen marker").setValue(true);
        this.showDistance = new Setting(this.settingsList, "Show distance").setValue(true);

        this.generalSettingsLabel = new Setting(this.settingsList, "label").setValue("General Settings");
        this.guiBlur = new Setting(this.settingsList, "GUI Blur").setValue(false);
        this.worldTime = new Setting(this.settingsList, "World Time").setValue(-14490).setMinMax(-22880, -6100);
        this.lookView = new Setting(this.settingsList, "Look View").setValue("Third").acceptedValues("Third", "Reverse", "First");
        this.snapModules = new Setting(this.settingsList, "Snap mods to other mods (GUI)").setValue(true);

        this.renderSettingsLabel = new Setting(this.settingsList, "label").setValue("Render Settings");
        this.showPotionInfo = new Setting(this.settingsList, "Show Potion info in inventory").setValue(true);
        this.showChatBackground = new Setting(this.settingsList, "Show chat background").setValue(true);
        this.showHudInDebug = new Setting(this.settingsList, "Show HUD while in debug view").setValue(false);
        this.shinyPots = new Setting(this.settingsList, "Shiny Pots").setValue(false);
        this.clearGlass = new Setting(this.settingsList, "Clear Glass").setValue("OFF").acceptedValues("OFF", "REGULAR", "ALL");
        this.redString = new Setting(this.settingsList, "Red String").setValue(false);
        this.transparentBackground = new Setting(this.settingsList, "Transparent background").setValue(false);

        this.crosshairSettingsLabel = new Setting(this.settingsList, "label").setValue("Crosshair Settings");
        this.customCrosshair = new Setting(this.settingsList, "Custom crosshair").setValue(false);
        this.crosshairOutline = new Setting(this.settingsList, "Outline").setValue(false);
        this.crosshairColor = new Setting(this.settingsList, "Color").setValue(-1).setMinMax(Integer.MIN_VALUE, Integer.MAX_VALUE);
        this.crosshairThickness = new Setting(this.settingsList, "Thickness").setValue(2f).setMinMax(1f, 2.5f);
        this.crosshairSize = new Setting(this.settingsList, "Size").setValue(4f).setMinMax(1f, 10f);
        this.crosshairGap = new Setting(this.settingsList, "Gap").setValue(1.75f).setMinMax(0f, 7.5f);

        this.colorOptionsLabel = new Setting(this.settingsList, "label").setValue("Color Options");
        this.defaultColor = new Setting(this.settingsList, "Default color").setValue(-1).setMinMax(Integer.MIN_VALUE, Integer.MAX_VALUE);

        this.miscellaneousOptionsLabel = new Setting(this.settingsList, "label").setValue("Miscellaneous Options");
        this.mainMenuTopGradient = new Setting(this.settingsList, "Main menu top gradient").setValue(true);
        this.mainMenuLightGradient = new Setting(this.settingsList, "Main menu light gradient").setValue(false);
        this.followMinecraftScale = new Setting(this.settingsList, "Follow Minecraft GUI Scale").setValue(true);

        this.pinnedServers = new ArrayList<>();
        //this.pinnedServers.add(new PinnedServer("MineHQ Network", "minehq.com"));
        //this.pinnedServers.add(new PinnedServer("VeltPvP", "veltpvp.com"));

        this.unrecommendedServers = new ArrayList<>();
        /*this.unrecommendedServers.add(
            new UnrecommendedServer("Purple Prison", UnrecommendedServer.REASON_P2W,
                ".*(\\.)?purple\\.wtf",                 ".*(\\.)?purpleprison\\.net",   ".*(\\.)?moxmc\\.net",
                ".*(\\.)?purpleprison\\.co",            ".*(\\.)?prisonfun\\.com",      ".*(\\.)?purpleprison\\.org",
                ".*(\\.)?minecraftsexserver\\.club",    ".*(\\.)?moxmc\\.com",          ".*(\\.)?purpleore\\.com",
                ".*(\\.)?purpleprison\\.xyz"
            )
        );
        this.unrecommendedServers.add(
            new UnrecommendedServer("LoverCraft", UnrecommendedServer.REASON_P2W,
                ".*(\\.)?loverfella\\.com"
            )
        );
        this.unrecommendedServers.add(
            new UnrecommendedServer("Glorious Realms", UnrecommendedServer.REASON_P2W,
                ".*(\\.)?gloriousrealms\\.com"
            )
        );
        this.unrecommendedServers.add(
            new UnrecommendedServer("Project Eden", UnrecommendedServer.REASON_P2W,
                ".*(\\.)?bnn\\.gg"
            )
        );
        this.unrecommendedServers.add(
            new UnrecommendedServer("Wild Prison", UnrecommendedServer.REASON_P2W,
                ".*(\\.)?wildprison\\.net"
            )
        );*/

        this.pushToTalk = Ref.getInstanceCreator().createKeyBinding("Voice Chat", 47, "CheatBreaker Client");
        this.openMenu = Ref.getInstanceCreator().createKeyBinding("Open Menu", 54, "CheatBreaker Client");
        this.openVoiceMenu = Ref.getInstanceCreator().createKeyBinding("Open Voice Menu", 25, "CheatBreaker Client");
        this.dragLook = Ref.getInstanceCreator().createKeyBinding("Drag to look", 56, "CheatBreaker Client");
        this.hideNames = Ref.getInstanceCreator().createKeyBinding("Hide name plates", 0, "CheatBreaker Client");

/*        GameSettingsBridge gameSettings = Ref.getMinecraft().bridge$getGameSettings();
        gameSettings.bridge$setKeyBindings(
                ArrayUtils.addAll(
                        gameSettings.bridge$getKeyBindings(),
                        this.pushToTalk,
                        this.openMenu,
                        this.openVoiceMenu,
                        this.dragLook,
                        this.hideNames
                )
        );*/
    }

    public boolean isDarkMode() {
        return this.theme.getValue().equals("Dark");
    }
}
