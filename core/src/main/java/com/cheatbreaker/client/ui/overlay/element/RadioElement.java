package com.cheatbreaker.client.ui.overlay.element;

import com.cheatbreaker.bridge.client.renderer.ThreadDownloadImageDataBridge;
import com.cheatbreaker.bridge.ref.Ref;
import com.cheatbreaker.bridge.util.ResourceLocationBridge;
import com.cheatbreaker.main.CheatBreaker;
import com.cheatbreaker.client.ui.fading.MinMaxFade;
import com.cheatbreaker.client.ui.mainmenu.element.ScrollableElement;
import com.cheatbreaker.client.ui.overlay.OverlayGui;
import com.cheatbreaker.client.ui.util.RenderUtil;
import com.cheatbreaker.client.ui.util.font.FontRegistry;
import com.cheatbreaker.client.util.dash.DashUtil;
import com.cheatbreaker.client.util.dash.Station;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class RadioElement extends DraggableElement {
    private final ResourceLocationBridge dashIcon = Ref.getInstanceCreator().createResourceLocation("client/dash-logo-54.png");
    private final ResourceLocationBridge playIcon = Ref.getInstanceCreator().createResourceLocation("client/icons/play-24.png");
    private final List<RadioStationElement> radioStationElements;
    private final MinMaxFade fade = new MinMaxFade(300L);
    private float IlIlllIIIIllIllllIllIIlIl;
    private boolean hovered;
    private final HorizontalSliderElement slider;
    private final ScrollableElement scrollableContainer;
    private final InputFieldElement filter;
    private final FlatButtonElement pin;

    public RadioElement() {
        this.slider = new HorizontalSliderElement(CheatBreaker.getInstance().getGlobalSettings().radioVolume);
        this.scrollableContainer = new ScrollableElement(this);
        this.filter = new InputFieldElement(FontRegistry.getPlayRegular16px(), "Filter", -11842741, -11842741);
        this.pin = new FlatButtonElement((Boolean) this.client.getGlobalSettings().pinRadio.getValue() ? "Unpin" : "Pin");
        this.radioStationElements = new ArrayList<>();
        for (Station station : CheatBreaker.getInstance().getRadioManager().getStations()) {
            this.radioStationElements.add(new RadioStationElement(this, station));
        }
    }

    public void lIIIIllIIlIlIllIIIlIllIlI() {
        this.setElementSize(this.x, this.y, this.width, this.height);
    }

    private boolean isFilterMatch(RadioStationElement radioStationElement) {
        return this.filter.getText().equals("") || radioStationElement.getStation().getName().toLowerCase().startsWith(this.filter.getText().toLowerCase()) || radioStationElement.getStation().getName().toLowerCase().startsWith(this.filter.getText().toLowerCase());
    }

    @Override
    public void setElementSize(float x, float y, float width, float height) {
        super.setElementSize(x, y, width, height);
        if (this.IlIlllIIIIllIllllIllIIlIl == 0.0f) {
            this.IlIlllIIIIllIllllIllIIlIl = height;
        }
        this.radioStationElements.sort(Comparator.comparing(llllIllIIIlIIIlIllIlIlIlI2 -> llllIllIIIlIIIlIllIlIlIlI2.getStation().getName()));
        this.slider.setElementSize(x, y + this.IlIlllIIIIllIllllIllIIlIl, width, 8);
        this.filter.setElementSize(x, y + this.IlIlllIIIIllIllllIllIIlIl + (float)8, width - (float)30, 13);
        this.pin.setElementSize(x + width - (float)30, y + this.IlIlllIIIIllIllllIllIIlIl + (float)8, (float)30, 13);
        this.scrollableContainer.setElementSize(x + width - (float)5, y + this.IlIlllIIIIllIllllIllIIlIl + (float)21, (float)5, 99);
        int n = 0;
        for (RadioStationElement radioStationElement : this.radioStationElements) {
            if (!this.isFilterMatch(radioStationElement)) continue;
            float f5 = y + (float)20 + this.IlIlllIIIIllIllllIllIIlIl + (float)n;
            radioStationElement.setElementSize(x, f5, width - (float)5, 20);
            n += 20;
        }
        this.scrollableContainer.setScrollAmount(n);
    }

    public boolean IIIIllIlIIIllIlllIlllllIl(float f, float f2) {
        return f > this.x && f < this.x + this.width && f2 > this.y && f2 < this.y + this.IlIlllIIIIllIllllIllIIlIl;
    }

    @Override
    protected void handleElementDraw(float f, float f2, boolean bl) {
        this.drag(f, f2);
        Ref.modified$drawRect(this.x, this.y, this.x + this.width, this.y + this.IlIlllIIIIllIllllIllIIlIl, -14540254);
        Station station = CheatBreaker.getInstance().getRadioManager().getCurrentStation();
        if (station != null) {
            if (station.currentResource == null && !station.getName().equals("")) {
                if (station.currentResource != null) {
                    this.mc.bridge$getTextureManager().bridge$deleteTexture(station.currentResource);
                    station.currentResource = null;
                }
                station.currentResource = Ref.getInstanceCreator().createResourceLocation("client/songs/" + station.getName());
                ThreadDownloadImageDataBridge threadDownloadImageData = Ref.getInstanceCreator().createThreadDownloadImageData(null, station.getCoverURL(), this.dashIcon, null);
                Ref.getMinecraft().bridge$getTextureManager().bridge$loadTexture(station.currentResource, threadDownloadImageData);
            }
            Ref.getGlBridge().bridge$color(1.0f, 1.0f, 1.0f, 1.0f);
            ResourceLocationBridge location = station.currentResource == null ? this.dashIcon : station.currentResource;
            RenderUtil.drawIcon(location, this.IlIlllIIIIllIllllIllIIlIl / 2.0f, this.x, this.y);
            float f3 = this.x + (float)50;
            if (this.mc.bridge$getCurrentScreen() == OverlayGui.getInstance()) {
                boolean bl2 = this.isMouseInside(f, f2) && f > this.x + (float)34 && f < this.x + (float)44 && f2 < this.y + this.IlIlllIIIIllIllllIllIIlIl;
                if (!DashUtil.isPlayerNotNull()) {
                    Ref.getGlBridge().bridge$color(1.0f, 1.0f, 1.0f, bl2 ? 1.0f : 0.8f);
                    RenderUtil.drawIcon(this.playIcon, (float)6, this.x + (float)34, this.y + 7.5f);
                } else {
                    Ref.modified$drawRect(this.x + (float)36, this.y + (float)9, this.x + (float)38, this.y + this.IlIlllIIIIllIllllIllIIlIl - (float)11, bl2 ? -1 : -1342177281);
                    Ref.modified$drawRect(this.x + (float)40, this.y + (float)9, this.x + (float)42, this.y + this.IlIlllIIIIllIllllIllIIlIl - (float)11, bl2 ? -1 : -1342177281);
                }
            } else {
                f3 = this.x + (float)34;
            }
            String string = station.getName();
            FontRegistry.getPlayRegular16px().drawString(string, f3, this.y + (float)4, -1);
            FontRegistry.getPlayRegular12px().drawString(station.getArtist(), f3, this.y + (float)14, -1342177281);
        }
        float f4 = this.fade.lIIIIlIIllIIlIIlIIIlIIllI(this.isMouseInside(f, f2) && bl);
        if (this.fade.IIIllIllIlIlllllllIlIlIII()) {
            this.setElementSize(this.x, this.y, this.width, this.IlIlllIIIIllIllllIllIIlIl + (float)120 * f4);
            this.hovered = true;
        } else if (!this.fade.IIIllIllIlIlllllllIlIlIII() && !this.isMouseInside(f, f2)) {
            this.hovered = false;
        }
        if (this.hovered) {
            Ref.getGlBridge().bridge$pushMatrix();
            Ref.getGlBridge().bridge$enableScissoring(); // GL_SCISSOR_TEST
            OverlayGui overlayGui = OverlayGui.getInstance();
            RenderUtil.lIIIIlIIllIIlIIlIIIlIIllI((int)this.x, (int)(this.y + this.IlIlllIIIIllIllllIllIIlIl), (int)(this.x + this.width), (int)(this.y + this.IlIlllIIIIllIllllIllIIlIl + (this.height - this.IlIlllIIIIllIllllIllIIlIl) * f4), (float)((int)((float)overlayGui.getResolution().bridge$getScaleFactor() * overlayGui.getScaleFactor())), (int)overlayGui.getScaledHeight());
            Ref.modified$drawRect(this.x, this.y + this.IlIlllIIIIllIllllIllIIlIl, this.x + this.width, this.y + this.height, -14540254);
            this.scrollableContainer.drawScrollable(f, f2, bl);
            for (RadioStationElement radioStationElement : this.radioStationElements) {
                if (!this.isFilterMatch(radioStationElement)) continue;
                radioStationElement.handleElementDraw(f, f2 - this.scrollableContainer.IllIIIIIIIlIlIllllIIllIII(), bl && !this.scrollableContainer.isDragClick() && !this.scrollableContainer.isMouseInside(f, f2));
            }
            this.scrollableContainer.handleElementDraw(f, f2, bl);
            this.slider.drawElement(f, f2, bl);
            this.filter.handleElementDraw(f, f2, bl);
            this.pin.handleElementDraw(f, f2, bl);
            Ref.getGlBridge().bridge$disableScissoring(); // GL_SCISSOR_TEST
            Ref.getGlBridge().bridge$popMatrix();
        }
    }

    @Override
    public void handleElementMouse() {
        this.scrollableContainer.handleElementMouse();
    }

    @Override
    public void handleElementUpdate() {
        this.filter.handleElementUpdate();
        this.pin.handleElementUpdate();
    }

    @Override
    public void handleElementClose() {
        this.filter.handleElementClose();
        this.pin.handleElementClose();
    }

    @Override
    public void handleElementKeyTyped(char c, int n) {
        this.filter.handleElementKeyTyped(c, n);
        this.pin.handleElementKeyTyped(c, n);
        this.scrollableContainer.handleElementKeyTyped(c, n);
        if (this.filter.lllIIIIIlIllIlIIIllllllII()) {
            this.lIIIIllIIlIlIllIIIlIllIlI();
        }
    }

    @Override
    public boolean handleMouseClickedInternal(float f, float f2, int n) {
        if (!this.filter.isMouseInside(f, f2) && this.filter.lllIIIIIlIllIlIIIllllllII()) {
            this.filter.lIIIIIIIIIlIllIIllIlIIlIl(false);
        }
        return false;
    }

    @Override
    public boolean handleElementMouseClicked(float f, float f2, int n, boolean bl) {
        this.filter.handleElementMouseClicked(f, f2, n, bl);
        if (this.filter.lllIIIIIlIllIlIIIllllllII() && n == 1 && this.filter.getText().equals("")) {
            this.lIIIIllIIlIlIllIIIlIllIlI();
        }
        if (!bl) {
            return false;
        }
        boolean bl2 = this.isMouseInside(f, f2) && f > this.x + (float) 34 && f < this.x + (float) 44 && f2 < this.y + this.IlIlllIIIIllIllllIllIIlIl;
        if (bl2) {
            if (!DashUtil.isPlayerNotNull()) {
                CheatBreaker.getInstance().getRadioManager().getCurrentStation().endStream();
            } else {
                DashUtil.end();
            }
        }
        float f3 = this.fade.lIIIIlIIllIIlIIlIIIlIIllI(this.isMouseInside(f, f2));
        if (this.fade.IllIllIIIlIIlllIIIllIllII()) {
            boolean bl4;
            this.slider.handleElementMouseClicked(f, f2, n, true);
            this.scrollableContainer.handleElementMouseClicked(f, f2, n, true);
            this.filter.handleElementMouseClicked(f, f2, n, true);
            this.pin.handleElementMouseClicked(f, f2, n, true);
            boolean bl5 = bl4 = f > (float)((int)this.x) && f < (float)((int)(this.x + this.width)) && f2 > (float)((int)(this.y + this.IlIlllIIIIllIllllIllIIlIl + (float)21)) && f2 < (float)((int)(this.y + this.IlIlllIIIIllIllllIllIIlIl + (float)21 + (this.height - this.IlIlllIIIIllIllllIllIIlIl - (float)21) * f3));
            if (bl4) {
                RadioStationElement element;
                Iterator iterator = this.radioStationElements.iterator();
                while (!(!iterator.hasNext() || this.isFilterMatch(element = (RadioStationElement)iterator.next()) && element.handleElementMouseClicked(f, f2 - this.scrollableContainer.IllIIIIIIIlIlIllllIIllIII(), n, bl))) {
                }
            }
            if (this.pin.isMouseInside(f, f2)) {
                this.client.getGlobalSettings().pinRadio.setValue(!((Boolean) this.client.getGlobalSettings().pinRadio.getValue()));
                this.pin.lIIIIlIIllIIlIIlIIIlIIllI((Boolean) this.client.getGlobalSettings().pinRadio.getValue() ? "Unpin" : "Pin");
            }
        }
        if (this.isMouseInside(f, f2) && f2 < this.y + this.IlIlllIIIIllIllllIllIIlIl && !bl2 && !this.slider.isMouseInside(f, f2) && !this.scrollableContainer.isMouseInside(f, f2)) {
            this.updateDraggingPosition(f, f2);
        }
        return super.handleElementMouseClicked(f, f2, n, true);
    }
}

