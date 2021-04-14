package framework.persistence;

import framework.models.Data;
import framework.models.account.Account;
import framework.observer.Observable;
import framework.observer.Observer;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

public abstract class ACCFile implements Observable {
	protected List<Account> accounts;
	protected List<Observer> observers=new ArrayList<>();

	public ACCFile() {
		accounts = new ArrayList();
	}
	public ACCFile(List<Account> accounts) {
		this.accounts = accounts;
	}

	public void addAccount(Account account) {
		this.accounts.add(account);
		notifyObservers();
	}

	public void removeAccount(String id) {
		for (Account a :
				accounts) {
			if (a.getId().equals(id)) {
				accounts.remove(a);
				System.out.println("removing");
				break;
			}
		}
		notifyObservers();
	}

	public void doAll(Consumer consumer) {
		for (Account account : accounts) {
			consumer.accept(account);
		}
	}

	public Account get(Predicate<Account> predicate) {
		for (Account a : accounts) {
			if (predicate.test(a))
				return a;
		}
		return null;
	}

	@Override
	public void attach(Observer observer) {
		observers.add(observer);
	}

	@Override
	public void remove(Observer observer) {
		observers.remove(observer);
	}

	@Override
	public abstract void notifyObservers();

	public abstract void updateAccount(Account account);
}