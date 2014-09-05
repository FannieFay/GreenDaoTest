/*
 * Copyright (C) 2011 Markus Junginger, greenrobot (http://greenrobot.de)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package de.greenrobot.daogenerator.gentest;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;

/**
 * Generates entities and DAOs for the example project DaoExample.
 * 
 * Run it as a Java application (not Android).
 * 
 * @author Markus
 */
public class ExampleDaoGenerator
{

    public static void main(String[] args) throws Exception
    {
        Schema schema = new Schema(1000, "com.example.testactionbar.db");

        // addNote(schema);
        // addCustomerOrder(schema);
        addBook(schema);

        new DaoGenerator().generateAll(schema, "../TestActionBar/src");
    }

    // private static void addNote(Schema schema)
    // {
    // Entity note = schema.addEntity("Note");
    // note.addIdProperty();
    // note.addStringProperty("text").notNull();
    // note.addStringProperty("comment");
    // note.addDateProperty("date");
    // note.implementsSerializable();
    // }
    //
    // private static void addCustomerOrder(Schema schema)
    // {
    // Entity customer = schema.addEntity("Customer");
    // customer.addIdProperty();
    // customer.addStringProperty("name").notNull();
    //
    // Entity order = schema.addEntity("Order");
    // order.setTableName("ORDERS"); // "ORDER" is a reserved keyword
    // order.addIdProperty();
    // Property orderDate = order.addDateProperty("date").getProperty();
    // Property customerId = order.addLongProperty("customerId").notNull().getProperty();
    // // order.addToOne(customer, customerId);
    //
    // ToMany customerToOrders = customer.addToMany(order, customerId);
    // customerToOrders.setName("orders");
    // customerToOrders.orderAsc(orderDate);
    // }

    private static void addBook(Schema schema)
    {
        Entity book = schema.addEntity("Book");
        book.addStringProperty("bookID").primaryKey().getProperty();
        book.addStringProperty("bookName");
        book.addStringProperty("bookUrl");
        book.addStringProperty("author");
        book.addStringProperty("state");
        book.addStringProperty("introduce");
        book.addStringProperty("lastChapterUrl");
        book.addStringProperty("lastChapterName");

        // Entity chapter = schema.addEntity("Chapter");
        // Property bookID = chapter.addStringProperty("bookID").getProperty();
        // chapter.addStringProperty("chapterName");
        // chapter.addStringProperty("chapterUrl");
        // book.addToMany(chapter, bookID);
    }
}
