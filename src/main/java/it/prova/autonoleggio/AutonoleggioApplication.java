package it.prova.autonoleggio;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import it.prova.autonoleggio.model.Auto;
import it.prova.autonoleggio.model.Ruolo;
import it.prova.autonoleggio.model.TipoAuto;
import it.prova.autonoleggio.model.TipoMotore;
import it.prova.autonoleggio.model.Utente;
import it.prova.autonoleggio.service.auto.AutoService;
import it.prova.autonoleggio.service.ruolo.RuoloService;
import it.prova.autonoleggio.service.utente.UtenteService;

@SpringBootApplication
public class AutonoleggioApplication implements CommandLineRunner {

	@Autowired
	private RuoloService ruoloServiceInstance;

	@Autowired
	private AutoService autoService;

	@Autowired
	private UtenteService utenteServiceInstance;

	public static void main(String[] args) {
		SpringApplication.run(AutonoleggioApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		if (ruoloServiceInstance.cercaPerCodice(Ruolo.ROLE_ADMIN) == null) {
			ruoloServiceInstance.inserisciNuovo(new Ruolo(Ruolo.ROLE_ADMIN));
		}

		if (ruoloServiceInstance.cercaPerCodice(Ruolo.ROLE_CLASSIC_USER) == null) {
			ruoloServiceInstance.inserisciNuovo(new Ruolo(Ruolo.ROLE_CLASSIC_USER));
		}

		Ruolo ruolo = ruoloServiceInstance.caricaSingoloElemento(1L);
		Utente admin = utenteServiceInstance.findByUsername("adminUsername");
		if (admin != null && admin.getRuoli().contains(ruolo)) {
			System.out.println("Admin admin già impostato");
		} else {
			Set<Ruolo> ruoli = new HashSet<>();
			ruoli.add(ruolo);
			Utente setAdmin = new Utente("adminUsername", "adminPassword", "adminPassword", "admin@example.com",
					"AdminNome", "AdminCognome", true, ruoli);
			utenteServiceInstance.inserisciNuovo(setAdmin);
			System.out.println("admin  settato");
		}

		Auto auto1 = Auto.builder().targa("AA123BB").tipologia(TipoAuto.BERLINA).marca("Audi").modello("A4")
				.cilindrata(1968).tipoMotore(TipoMotore.DIESEL).numeroPosti(5).cambioAutomatico(true)
				.dataImmatricolazione(LocalDate.of(2023, 7, 1)).descrizione("Berlina di lusso con prestazioni elevate")
				.prezzoPerGiornata(80.0f).imageUrl("https://i.ibb.co/y4V6xJ7/BERLINA-A4-AUDI-removebg-preview.png").build();

		Auto auto2 = Auto.builder().targa("CC456DD").tipologia(TipoAuto.SUV).marca("Ford").modello("Kuga")
				.cilindrata(1997).tipoMotore(TipoMotore.DIESEL).numeroPosti(5).cambioAutomatico(true)
				.dataImmatricolazione(LocalDate.of(2023, 3, 22)).descrizione("SUV spazioso e confortevole")
				.prezzoPerGiornata(50.0f).imageUrl("https://i.ibb.co/4K6Z9KN/SUV-FORD-KUGA.jpg").build();

		Auto auto3 = Auto.builder().targa("EE789FF").tipologia(TipoAuto.CABRIOLET).marca("BMW").modello("Serie 4")
				.cilindrata(1998).tipoMotore(TipoMotore.BENZINA).numeroPosti(4).cambioAutomatico(true)
				.dataImmatricolazione(LocalDate.of(2022, 8, 10)).descrizione("Elegante cabrio per viaggi di piacere")
				.prezzoPerGiornata(70.0f).imageUrl("https://i.ibb.co/PTT7kgw/bmw-serie-4-cabrio.webp").build();

		Auto auto4 = Auto.builder().targa("AA123BC").tipologia(TipoAuto.BERLINA).marca("Fiat").modello("Punto")
				.cilindrata(1242).tipoMotore(TipoMotore.BENZINA).numeroPosti(5).cambioAutomatico(false)
				.dataImmatricolazione(LocalDate.of(2022, 6, 15)).descrizione("Auto compatta ideale per la città")
				.prezzoPerGiornata(30.5f).imageUrl("https://i.ibb.co/QK93XSb/BERLINA-FIAT-TIPO.jpg").build();

		List<Auto> autoList = new ArrayList<>();
		autoList.addAll(Arrays.asList(
				Auto.builder().targa("CC567DD").tipologia(TipoAuto.STATION_WAGON).marca("Volkswagen").modello("Passat")
						.cilindrata(1968).tipoMotore(TipoMotore.DIESEL).numeroPosti(5).cambioAutomatico(true)
						.dataImmatricolazione(LocalDate.of(2022, 11, 30))
						.descrizione("Station wagon confortevole e spaziosa").prezzoPerGiornata(65.0f)
						.imageUrl("https://i.ibb.co/g6tY79b/Volkswagen-passat.png").build(),
				Auto.builder().targa("DD678EE").tipologia(TipoAuto.COUPE).marca("Mercedes").modello("C-Class Coupe")
						.cilindrata(1991).tipoMotore(TipoMotore.BENZINA).numeroPosti(4).cambioAutomatico(true)
						.dataImmatricolazione(LocalDate.of(2023, 1, 15))
						.descrizione("Coupe elegante con motore potente").prezzoPerGiornata(85.0f)
						.imageUrl("https://i.ibb.co/F5y0w9S/Mercedes-classe-C-Coup.jpg").build(),
				Auto.builder().targa("EE789GG").tipologia(TipoAuto.CABRIOLET).marca("Audi").modello("A3 Cabriolet")
						.cilindrata(1395).tipoMotore(TipoMotore.IBRIDO).numeroPosti(4).cambioAutomatico(true)
						.dataImmatricolazione(LocalDate.of(2023, 4, 5))
						.descrizione("Cabriolet ibrida per una guida ecologica").prezzoPerGiornata(75.0f)
						.imageUrl("https://i.ibb.co/StVXGGZ/audi-a3-cabrio-promo.webp").build(),
				Auto.builder().targa("FF890HH").tipologia(TipoAuto.SUV).marca("Toyota").modello("RAV4").cilindrata(2494)
						.tipoMotore(TipoMotore.IBRIDO).numeroPosti(5).cambioAutomatico(true)
						.dataImmatricolazione(LocalDate.of(2022, 9, 21)).descrizione("SUV ibrido con alta efficienza")
						.prezzoPerGiornata(70.0f).imageUrl("https://i.ibb.co/r2YWxC9/toyota-rav4-top-ant.jpg").build(),
				Auto.builder().targa("GG901II").tipologia(TipoAuto.BERLINA).marca("Tesla").modello("Model S")
						.cilindrata(0).tipoMotore(TipoMotore.ELETTRICO).numeroPosti(5).cambioAutomatico(true)
						.dataImmatricolazione(LocalDate.of(2023, 5, 14))
						.descrizione("Berlina elettrica con autonomia elevata").prezzoPerGiornata(100.0f)
						.imageUrl("https://i.ibb.co/3NQR2Qm/Meet-Your-Tesla-Model-S.png").build(),
				Auto.builder().targa("HH012JJ").tipologia(TipoAuto.SUV).marca("BMW").modello("X5").cilindrata(2998)
						.tipoMotore(TipoMotore.BENZINA).numeroPosti(5).cambioAutomatico(true)
						.dataImmatricolazione(LocalDate.of(2023, 2, 20)).descrizione("SUV di lusso con motore potente")
						.prezzoPerGiornata(90.0f).imageUrl("https://i.ibb.co/ZNb23Yg/bmw-x5.jpg").build(),
				Auto.builder().targa("II123KK").tipologia(TipoAuto.STATION_WAGON).marca("Volvo").modello("V60")
						.cilindrata(1969).tipoMotore(TipoMotore.DIESEL).numeroPosti(5).cambioAutomatico(true)
						.dataImmatricolazione(LocalDate.of(2023, 6, 11)).descrizione("Station wagon sicura e spaziosa")
						.prezzoPerGiornata(68.0f).imageUrl("https://i.ibb.co/y04hCV2/VOLVO-V60.jpg").build(),
				Auto.builder().targa("JJ234LL").tipologia(TipoAuto.COUPE).marca("Audi").modello("TT Coupe")
						.cilindrata(1984).tipoMotore(TipoMotore.BENZINA).numeroPosti(4).cambioAutomatico(true)
						.dataImmatricolazione(LocalDate.of(2023, 7, 2))
						.descrizione("Coupe sportiva con design accattivante").prezzoPerGiornata(80.0f)
						.imageUrl("https://i.ibb.co/cwNMrbR/Audi-TT-removebg-preview.png").build(),
				Auto.builder().targa("KK345MM").tipologia(TipoAuto.CABRIOLET).marca("Mini").modello("Convertible")
						.cilindrata(1499).tipoMotore(TipoMotore.BENZINA).numeroPosti(4).cambioAutomatico(true)
						.dataImmatricolazione(LocalDate.of(2022, 12, 10))
						.descrizione("Cabriolet compatta e divertente da guidare").prezzoPerGiornata(65.0f)
						.imageUrl("https://i.ibb.co/Fz2LbH5/new-mini-cabrio-2021.jpg").build(),
				Auto.builder().targa("LL456NN").tipologia(TipoAuto.SUV).marca("Nissan").modello("Qashqai")
						.cilindrata(1598).tipoMotore(TipoMotore.GAS).numeroPosti(5).cambioAutomatico(true)
						.dataImmatricolazione(LocalDate.of(2023, 3, 1)).descrizione("SUV compatto ed economico")
						.prezzoPerGiornata(55.0f).imageUrl("https://i.ibb.co/grZ1JVT/NISSAN-QASHQAI.jpg").build(),
				Auto.builder().targa("MM567OO").tipologia(TipoAuto.BERLINA).marca("Mercedes").modello("E-Class")
						.cilindrata(1950).tipoMotore(TipoMotore.DIESEL).numeroPosti(5).cambioAutomatico(true)
						.dataImmatricolazione(LocalDate.of(2023, 4, 17))
						.descrizione("Berlina di lusso con comfort eccezionale").prezzoPerGiornata(85.0f)
						.imageUrl("https://i.ibb.co/ZYW3m65/MERCEDES-E-CLASS.jpg").build(),
				Auto.builder().targa("NN678PP").tipologia(TipoAuto.STATION_WAGON).marca("Skoda").modello("Octavia")
						.cilindrata(1598).tipoMotore(TipoMotore.DIESEL).numeroPosti(5).cambioAutomatico(true)
						.dataImmatricolazione(LocalDate.of(2022, 10, 8))
						.descrizione("Station wagon affidabile e spaziosa").prezzoPerGiornata(60.0f)
						.imageUrl("https://i.ibb.co/0qfcXDP/skoda-octavia-combi-2024.jpg").build(),
				Auto.builder().targa("OO789QQ").tipologia(TipoAuto.COUPE).marca("Porsche").modello("911")
						.cilindrata(2981).tipoMotore(TipoMotore.BENZINA).numeroPosti(4).cambioAutomatico(true)
						.dataImmatricolazione(LocalDate.of(2023, 6, 25))
						.descrizione("Coupe sportiva con prestazioni straordinarie").prezzoPerGiornata(150.0f)
						.imageUrl("https://i.ibb.co/HXrGv5M/porsche-911-carrera-ant.jpg").build(),
				Auto.builder().targa("PP890RR").tipologia(TipoAuto.CABRIOLET).marca("Jaguar").modello("F-Type")
						.cilindrata(1997).tipoMotore(TipoMotore.BENZINA).numeroPosti(2).cambioAutomatico(true)
						.dataImmatricolazione(LocalDate.of(2022, 8, 30)).descrizione("Cabriolet sportiva ed elegante")
						.prezzoPerGiornata(130.0f)
						.imageUrl("https://i.ibb.co/sqPDbgX/new-jaguar-f-type-convertble-cover.jpg").build(),
				Auto.builder().targa("QQ901SS").tipologia(TipoAuto.SUV).marca("Hyundai").modello("Tucson")
						.cilindrata(1591).tipoMotore(TipoMotore.IBRIDO).numeroPosti(5).cambioAutomatico(true)
						.dataImmatricolazione(LocalDate.of(2022, 9, 18))
						.descrizione("SUV ibrido versatile ed efficiente").prezzoPerGiornata(65.0f)
						.imageUrl("https://i.ibb.co/jbFZH25/Hyundai-Tucson-3.webp").build(),
				Auto.builder().targa("RR012TT").tipologia(TipoAuto.BERLINA).marca("Peugeot").modello("508")
						.cilindrata(1598).tipoMotore(TipoMotore.DIESEL).numeroPosti(5).cambioAutomatico(true)
						.dataImmatricolazione(LocalDate.of(2022, 7, 12)).descrizione("Berlina elegante e moderna")
						.prezzoPerGiornata(60.0f).imageUrl("https://i.ibb.co/nfDn34B/PEGEUOT-508.jpg").build(),
				Auto.builder().targa("SS123UU").tipologia(TipoAuto.STATION_WAGON).marca("Subaru").modello("Outback")
						.cilindrata(2498).tipoMotore(TipoMotore.BENZINA).numeroPosti(5).cambioAutomatico(true)
						.dataImmatricolazione(LocalDate.of(2023, 5, 1))
						.descrizione("Station wagon robusta per ogni terreno").prezzoPerGiornata(75.0f)
						.imageUrl("https://i.ibb.co/bvXdLjT/SUBARU-OUATBACK.jpg").build(),
				Auto.builder().targa("TT234VV").tipologia(TipoAuto.COUPE).marca("Lexus").modello("RC").cilindrata(2500)
						.tipoMotore(TipoMotore.IBRIDO).numeroPosti(4).cambioAutomatico(true)
						.dataImmatricolazione(LocalDate.of(2023, 2, 11)).descrizione("Coupe ibrida con design elegante")
						.prezzoPerGiornata(90.0f).imageUrl("https://i.ibb.co/NNCryGL/Lexus-RC.jpg").build(),
				Auto.builder().targa("UU345WW").tipologia(TipoAuto.CABRIOLET).marca("Audi").modello("TT Roadster")
						.cilindrata(1984).tipoMotore(TipoMotore.BENZINA).numeroPosti(2).cambioAutomatico(true)
						.dataImmatricolazione(LocalDate.of(2023, 3, 29)).descrizione("Cabriolet sportiva e compatta")
						.prezzoPerGiornata(85.0f).imageUrl("https://i.ibb.co/zmV3mtQ/audi-tt-roadster-2015-10-removebg-preview.png")
						.build()));                          

		salvaAutoSeNonEsiste(auto1);
		salvaAutoSeNonEsiste(auto2);
		salvaAutoSeNonEsiste(auto3);
		salvaAutoSeNonEsiste(auto4);
		for (Auto auto : autoList) {
			salvaAutoSeNonEsiste(auto);
		}
	}

	private void salvaAutoSeNonEsiste(Auto auto) {
		Optional<Auto> existingAuto = autoService.findByTarga(auto.getTarga());
		if (existingAuto.isEmpty()) {
			autoService.inserisciNuovo(auto);
			System.out.println("Auto inserita con successo");
		}
	}

}
