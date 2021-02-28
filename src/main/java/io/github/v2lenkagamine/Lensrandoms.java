package io.github.v2lenkagamine;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import io.github.v2lenkagamine.core.init.Items;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(Lensrandoms.MOD_ID)
public class Lensrandoms {
		public static final Logger LOGGER = LogManager.getLogger();
		public static final String MOD_ID = "lensrandoms";
		public Lensrandoms() {
			IEventBus itembus = FMLJavaModLoadingContext.get().getModEventBus();
			itembus.addListener(this::setup);
			
			Items.ITEMS.register(itembus);
			
			FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);

			MinecraftForge.EVENT_BUS.register(this);

		}
		private void setup(final FMLCommonSetupEvent event) {

		}
}
