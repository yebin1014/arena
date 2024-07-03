package me.yenu.arena.Item;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionData;
import org.bukkit.potion.PotionType;

import java.util.Arrays;

public class ItemManger {

    public static ItemStack itemStack(Material type, int amount, String s, String... args) {
        ItemStack stack = new ItemStack(type, amount);
        ItemStack potion = new ItemStack(Material.POTION);
        ItemMeta pmeta = (PotionMeta) potion.getItemMeta();
        ItemMeta meta = stack.getItemMeta();
        meta.setDisplayName(s);
        meta.setLore(Arrays.asList(args));
        stack.setItemMeta(meta);
        return stack;
    }

    public static ItemStack Potion() { // 즉시회복
        ItemStack potion = new ItemStack(Material.SPLASH_POTION);
        PotionMeta meta = (PotionMeta) potion.getItemMeta();
        meta.setBasePotionData(new PotionData(PotionType.INSTANT_HEAL));
        meta.setDisplayName(ChatColor.LIGHT_PURPLE + "즉시회복");
        meta.setLore(Arrays.asList(ChatColor.WHITE + "회복용"));
        potion.setItemMeta(meta);
        return potion;
    }

    public static ItemStack Ppotion() { // 독포션
        ItemStack potion = new ItemStack(Material.SPLASH_POTION);
        PotionMeta meta = (PotionMeta) potion.getItemMeta();
        meta.setBasePotionData(new PotionData(PotionType.POISON));
        meta.setDisplayName(ChatColor.DARK_GREEN + "독포션");
        meta.setLore(Arrays.asList(ChatColor.WHITE + "적군용"));
        potion.setItemMeta(meta);
        return potion;
    }


    public static final ItemStack axe = itemStack(Material.DIAMOND_AXE, 1, ChatColor.WHITE + "다이아 도끼", ChatColor.WHITE + "기본 도끼");
    public static final ItemStack snowball = itemStack(Material.SNOWBALL, 1,ChatColor.AQUA + "슬로우 볼", ChatColor.WHITE + "적 근처에 버릴 시 상대방이 5초간 슬로우에 걸린다");
    public static final ItemStack icewall = itemStack(Material.ICE, 1, ChatColor.BLUE + "얼음벽", ChatColor.WHITE + "우클릭 시 플레이어 앞에 3x3사이즈의 얼음벽이 3초간 생성된다");
    public static final ItemStack ga = itemStack(Material.GOLDEN_APPLE, 1, ChatColor.YELLOW + "황금사과", ChatColor.WHITE + "회복용");
    public static final ItemStack food = itemStack(Material.COOKED_BEEF, 64,  ChatColor.WHITE + "스테이크", ChatColor.WHITE + "음식");

}
