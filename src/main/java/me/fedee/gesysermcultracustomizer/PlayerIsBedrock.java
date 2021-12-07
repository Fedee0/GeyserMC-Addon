package me.fedee.gesysermcultracustomizer;
import org.bukkit.entity.Player;
import java.util.UUID;

import me.TechsCode.UltraCustomizer.UltraCustomizer;
import me.TechsCode.UltraCustomizer.base.item.XMaterial;
import me.TechsCode.UltraCustomizer.scriptSystem.objects.Argument;
import me.TechsCode.UltraCustomizer.scriptSystem.objects.Child;
import me.TechsCode.UltraCustomizer.scriptSystem.objects.Element;
import me.TechsCode.UltraCustomizer.scriptSystem.objects.ElementInfo;
import me.TechsCode.UltraCustomizer.scriptSystem.objects.OutcomingVariable;
import me.TechsCode.UltraCustomizer.scriptSystem.objects.ScriptInstance;
import me.TechsCode.UltraCustomizer.scriptSystem.objects.datatypes.DataType;
import org.geysermc.floodgate.api.FloodgateApi;


public class PlayerIsBedrock extends Element {
    public PlayerIsBedrock(UltraCustomizer plugin) {
        super(plugin);
    }

    public String getName() {
        return "Get Bedrock Player";
    }

    public String getInternalName() {
        return "get-bedrock-player";
    }

    public boolean isHidingIfNotCompatible() {
        return false;
    }

    public XMaterial getMaterial() {
        return XMaterial.BEDROCK;
    }

    public String[] getDescription() {
        return new String[] { "Check if the specified player", "joined with Minecraft Bedrock Edition" };
    }

    public Argument[] getArguments(ElementInfo elementInfo) {
        return new Argument[] { new Argument("player", "Player", DataType.PLAYER, elementInfo) };
    }

    public OutcomingVariable[] getOutcomingVariables(final ElementInfo elementInfo) {
        return new OutcomingVariable[0];
    }

    public Child[] getConnectors(final ElementInfo elementInfo) {
        return new Child[]{
                new Child(elementInfo, "yes") {
                    public String getName() {
                        return "Joined with Bedrock";
                    }

                    public String[] getDescription() {
                        return new String[]{"Will be executed if the player", "joined with Bedrock Client"};
                    }

                    public XMaterial getIcon() {
                        return XMaterial.LIME_STAINED_GLASS_PANE;
                    }
                }, new Child(elementInfo, "no") {
            public String getName() {
                return "Didn't Joined with Bedrock";
            }

            public String[] getDescription() {
                return new String[]{"Will be executed if the player", "didn't joined with Bedrock Client"};
            }

            public XMaterial getIcon() {
                return XMaterial.RED_STAINED_GLASS_PANE;
            }
        }};
    }

    public void run(ElementInfo info, ScriptInstance instance) {

        FloodgateApi.getInstance();

        Player player = (Player) getArguments(info)[0].getValue(instance);

        UUID uuid = player.getUniqueId();


        if (FloodgateApi.getInstance().isFloodgatePlayer(uuid)) {
                getConnectors(info)[0].run(instance);
            } else {
            getConnectors(info)[1].run(instance);

        }        }
    }
