package com.qoonnect.rectem_api.util;

import com.qoonnect.rectem_api.configuration.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.function.Consumer;
import java.util.function.Function;

public class TransactionUtil {
    public static void executeTransaction(Consumer<Session> transact) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            transact.accept(session);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
                System.out.println("rollback transaction");
            }
            throw new RuntimeException("An error occurred while trying to process transaction, transaction will be rolled back", e);
        }
    }

    public static <R> R executeTransaction(Function<Session, R> transact) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            R result = transact.apply(session);
            transaction.commit();
            return result;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException("An error occurred while trying to process transaction, transaction will be rolled back", e);
        }
    }
}
