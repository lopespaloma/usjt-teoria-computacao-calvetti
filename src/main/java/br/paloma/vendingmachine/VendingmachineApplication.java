package br.paloma.vendingmachine;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.util.Scanner;

@SpringBootApplication
public class VendingmachineApplication {

	public static void main(String[] args) {
		SpringApplication.run(VendingmachineApplication.class, args);
	}
	public static class VendingMachine {
		public static void main(String[] args) {
			Scanner scanner = new Scanner(System.in);

			int precoA = 6;
			int precoB = 7;
			int precoC = 8;
			int limite = 10;

			System.out.println("Bem-vindo à Vending Machine!");
			System.out.println("Por favor, insira uma nota de 1, 2 ou 5.");

			int totalRecebido = 0;
			int nota;

			while (totalRecebido < limite) {
				nota = scanner.nextInt();

				if (nota != 1 && nota != 2 && nota != 5) {
					System.out.println("Nota inválida. Por favor, insira uma nota de 1, 2 ou 5.");
					continue;
				}
				totalRecebido += nota;
				System.out.println("Total recebido: R$" + totalRecebido + ",00.");

				if (totalRecebido >= limite) {
					break;
				}
			}

			System.out.println("Por favor, escolha o doce desejado:");
			System.out.println("A - R$6,00");
			System.out.println("B - R$7,00");
			System.out.println("C - R$8,00");
			char escolha = scanner.next().charAt(0);

			int precoEscolhido = 0;

			switch (escolha) {
				case 'A':
					precoEscolhido = precoA;
					break;
				case 'B':
					precoEscolhido = precoB;
					break;
				case 'C':
					precoEscolhido = precoC;
					break;
				default:
					System.out.println("Opção inválida.");
					scanner.close();
					return;
			}

			int troco = totalRecebido - precoEscolhido;
			if (troco >= 0) {
				System.out.println("Doce entregue! Obrigado pela sua compra.");
				if (troco > 0) {
					System.out.println("Troco: R$" + troco + ",00");
				}
			} else {
				System.out.println("Você não tem dinheiro suficiente para comprar este doce.");
			}

			scanner.close();
		}
	}
}