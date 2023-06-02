package com.cheatbreaker.impl.ref;

import com.cheatbreaker.bridge.client.gui.GuiBossOverlayBridge;
import com.cheatbreaker.bridge.entity.boss.BossStatusBridge;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.BossInfoClient;

import java.util.Map;
import java.util.UUID;

public class BridgedBossStatus implements BossStatusBridge {
    private Map<UUID, BossInfoClient> getMapBossInfos() {
        return ((GuiBossOverlayBridge) Minecraft.getMinecraft().ingameGUI.getBossOverlay()).bridge$mapBossInfos();
    }

    public String bridge$getBossName() {
        if (!this.getMapBossInfos().isEmpty()) {
            for (BossInfoClient inf : ((GuiBossOverlayBridge) Minecraft.getMinecraft().ingameGUI.getBossOverlay()).bridge$mapBossInfos().values()) {
                return inf.getName().getFormattedText();
            }
        }
        return null;
    }

    public int bridge$getStatusBarTime() {
        if (!this.getMapBossInfos().isEmpty()) {
            for (BossInfoClient inf : ((GuiBossOverlayBridge) Minecraft.getMinecraft().ingameGUI.getBossOverlay()).bridge$mapBossInfos().values()) {
                return (int) (inf.getPercent() * 100);
            }
        }
        return -1;
    }
}
