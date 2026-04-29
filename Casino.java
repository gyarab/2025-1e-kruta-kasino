import java.util.Random;
import java.util.Scanner;

public class Casino {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        int penize = 1000;
        int volbaHry = 0;

        System.out.println("=========================================");
        System.out.println("             CASINO Arabská              ");
        System.out.println("=========================================");

        boolean vCasinu = true;
        while (penize > 0 && vCasinu) {
            System.out.println();
            System.out.println("--- HLAVNÍ MENU ---");
            System.out.println("Zůstatek: " + penize + " Kč");
            System.out.println("1 - Ruleta");
            System.out.println("2 - Blackjack");
            System.out.println("3 - Odejít z casina");
            System.out.print("Vyber si hru: ");

            volbaHry = scanner.nextInt();

            if (volbaHry == 1) {
                boolean hraRuletu = true;

                while (hraRuletu && penize > 0) {
                    System.out.println();
                    System.out.println("--- RULETA ---");
                    System.out.print("Kolik chceš vsadit? ");
                    int sazka = scanner.nextInt();

                    if (sazka <= 0 || sazka > penize) {
                        System.out.println("Nedostatek Peněz");
                    } else {
                        penize = penize - sazka;

                        int typSazky = 0;
                        while (typSazky != 1 && typSazky != 2) {
                            System.out.println();
                            System.out.println("Vyber typ sázky?");
                            System.out.println("1 - Přesné číslo (Výhra 35x)");
                            System.out.println("2 - Barva (Červená / Černá / Zelená)");
                            System.out.print("Volba: ");
                            typSazky = scanner.nextInt();
                        }

                        int hadaneCislo = -1;
                        int volbaBarvy = -1;

                        if (typSazky == 1) {
                            while (hadaneCislo < 0 || hadaneCislo > 36) {
                                System.out.print("Jaké číslo (0-36)? ");
                                hadaneCislo = scanner.nextInt();
                            }
                        } else if (typSazky == 2) {
                            while (volbaBarvy != 1 && volbaBarvy != 2 && volbaBarvy != 3) {
                                System.out.print("1 - Červená, 2 - Černá, 3 - Zelená: ");
                                volbaBarvy = scanner.nextInt();
                            }
                        }

                        System.out.println();
                        System.out.println("Kulička se točí");
                        try {
                            Thread.sleep(1500);
                        } catch (Exception e) {
                        }

                        int vyherniCislo = random.nextInt(37);
                        System.out.println();
                        System.out.println("Padlo číslo: " + vyherniCislo);

                        if (typSazky == 1) {
                            if (hadaneCislo == vyherniCislo) {
                                System.out.println("Vyhráváš.");
                                penize = penize + (sazka * 36);
                            } else {
                                System.out.println("Prohra.");
                            }
                        } else if (typSazky == 2) {
                            if (vyherniCislo == 0 && volbaBarvy == 3) {
                                System.out.println("Zelená, Vyhráváš.");
                                penize = penize + (sazka * 36);
                            } else if (vyherniCislo != 0 && vyherniCislo % 2 != 0 && volbaBarvy == 1) {
                                System.out.println("Červená, Vyhráváš.");
                                penize = penize + (sazka * 2);
                            } else if (vyherniCislo != 0 && vyherniCislo % 2 == 0 && volbaBarvy == 2) {
                                System.out.println("Černá, Vyhráváš.");
                                penize = penize + (sazka * 2);
                            } else {
                                System.out.println("Prohráváš.");
                            }
                        } else {
                            System.out.println("Špatná volba sázky");
                        }
                    }

                    if (penize > 0) {
                        System.out.println();
                        System.out.println("Aktuální zůstatek: " + penize + " Kč");
                        System.out.print("Chceš hrát další kolo? (1 - Ano, 2 - Zpět do menu): ");
                        int pokracovat = scanner.nextInt();
                        if (pokracovat != 1) {
                            hraRuletu = false;
                        }
                    }
                }
            } else if (volbaHry == 2) {
                boolean hraBlackjack = true;

                while (hraBlackjack && penize > 0) {
                    System.out.println();
                    System.out.println("--- BLACKJACK ---");
                    System.out.print("Kolik chces vsadit? ");
                    int sazka = scanner.nextInt();

                    if (sazka <= 0 || sazka > penize) {
                        System.out.println("Neplatná sázka");
                    } else {
                        penize = penize - sazka;

                        int hracKarta1 = random.nextInt(10) + 2;
                        int hracKarta2 = random.nextInt(10) + 2;
                        int hracKarty = hracKarta1 + hracKarta2;
                        String hracKartyText = hracKarta1 + ", " + hracKarta2;

                        int dealerKarta1 = random.nextInt(10) + 2;
                        int dealerKarta2 = random.nextInt(10) + 2;
                        int dealerKarty = dealerKarta1 + dealerKarta2;

                        System.out.println();
                        System.out.println("Rozdávám karty");
                        try {
                            Thread.sleep(1000);
                        } catch (Exception e) {
                        }

                        System.out.println("Tvoje karty: " + hracKartyText + " (Součet: " + hracKarty + ")");
                        System.out.println("Dealer má: " + dealerKarta1 + " a ?");

                        boolean hracTaha = true;

                        while (hracTaha) {
                            if (hracKarty > 21) {
                                System.out.println();
                                System.out.println("Máš přes 21. Prohráváš");
                                hracTaha = false;
                            } else if (hracKarty == 21) {
                                System.out.println();
                                System.out.println("Blackjack");
                                hracTaha = false;
                            } else {
                                System.out.println();
                                System.out.print("Hit nebo Stand? (1 - Hit, 2 - Stand): ");
                                int dalsi = scanner.nextInt();
                                if (dalsi == 1) {
                                    int novaKarta = random.nextInt(10) + 2;
                                    hracKarty = hracKarty + novaKarta;
                                    hracKartyText = hracKartyText + ", " + novaKarta;

                                    System.out.println("Dostals kartu v hodnotě: " + novaKarta);
                                    try {
                                        Thread.sleep(500);
                                    } catch (Exception e) {
                                    }
                                    System.out.println("Tvoje karty teď jsou: " + hracKartyText + " (Součet: " + hracKarty + ")");
                                } else {
                                    hracTaha = false;
                                }
                            }
                        }

                        if (hracKarty <= 21) {
                            System.out.println();
                            System.out.println("Na řadě je dealer");
                            System.out.println();
                            try {
                                Thread.sleep(1000);
                            } catch (Exception e) {
                            }

                            System.out.println("Dealer odkrývá skrytou kartu: " + dealerKarta2);
                            System.out.println();
                            System.out.println("Dealer má karty: " + dealerKarta1 + ", " + dealerKarta2 + " (Součet: " + dealerKarty + ")");
                            try {
                                Thread.sleep(1000);
                            } catch (Exception e) {
                            }

                            while (dealerKarty < 17) {
                                int novaKartaDealera = random.nextInt(10) + 2;
                                dealerKarty = dealerKarty + novaKartaDealera;
                                System.out.println();
                                System.out.println("Dealer si vytáhl kartu v hodnotě: " + novaKartaDealera);
                                System.out.println("Jeho součet je teď: " + dealerKarty);
                                try {
                                    Thread.sleep(1000);
                                } catch (Exception e) {
                                }
                            }

                            System.out.println();
                            if (dealerKarty > 21) {
                                System.out.println("Dealer přetáhl Vyhráváš.");
                                penize = penize + (sazka * 2);
                            } else if (hracKarty > dealerKarty) {
                                System.out.println("Máš více než dealer Vyhráváš.");
                                penize = penize + (sazka * 2);
                            } else if (hracKarty == dealerKarty) {
                                System.out.println("Remíza. Peníze se ti vrací.");
                                penize = penize + sazka;
                            } else {
                                System.out.println("Dealer má víc. Prohráváš.");
                            }
                        }
                    }

                    if (penize > 0) {
                        System.out.println();
                        System.out.println("Aktuální zůstatek: " + penize + " Kč");
                        System.out.print("Chceš hrát další kolo? (1 - Ano, 2 - Zpět do menu): ");
                        int pokracovat = scanner.nextInt();
                        if (pokracovat != 1) {
                            hraBlackjack = false;
                        }
                    }
                }
            } else if (volbaHry == 3) {
                vCasinu = false;
            } else {
                System.out.println();
                System.out.println("Chybná Volba.");
            }
        }

        System.out.println();
        if (penize <= 0) {
            System.out.println("Prohál jsi všechny peníze.");
        } else {
            System.out.println("Finální zůstatek: " + penize + " Kč.");
        }
    }
}