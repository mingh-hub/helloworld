package com.mingh.learn.stream;

import com.google.common.collect.Lists;
import com.mingh.learn.beans.Book;
import com.mingh.learn.common.enums.CommonEnum;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.text.Collator;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

import static java.util.stream.Collectors.toList;
import static org.junit.Assert.assertEquals;

/**
 * Java 8 Stream Sorted test
 */
@Slf4j
public class SortTest {

    private static final List<Book> books = Lists.newArrayList();

    @Test
    public void sortBooksByPrice() {
        List<Book> sortedBookList = books.stream().sorted((a, b) -> a.getPrice().compareTo(b.getPrice())).collect(toList());
        log.info("sorted result sortedBookList is {}", sortedBookList);
        assertEquals(CommonEnum.BookType.FORTRESS_BESIEGED.getPrice(), sortedBookList.get(0).getPrice());

        List<Book> sortedBookList2 = books.stream().sorted(Comparator.comparing(Book::getPrice)).collect(toList());
        log.info("sorted result sortedBookList2 is {}", sortedBookList2);
        assertEquals(CommonEnum.BookType.FORTRESS_BESIEGED.getPrice(), sortedBookList2.get(0).getPrice());

        List<Book> sortedBookList3 = books.stream().sorted(Comparator.comparing(
                Book::getPrice,
                Double::compareTo
        )).collect(toList());
        log.info("sorted result sortedBookList3 is {}", sortedBookList3);
        assertEquals(CommonEnum.BookType.FORTRESS_BESIEGED.getPrice(), sortedBookList3.get(0).getPrice());
    }

    @Test
    public void sortBooksByZhCNBookName() {
        Collator zhCNBookNameCollator = Collator.getInstance(Locale.SIMPLIFIED_CHINESE);
        List<Book> sortedBookList = books.stream().sorted(Comparator.comparing(
                Book::getBookName,
                zhCNBookNameCollator
        )).collect(toList());
        log.info("sorted result sortedBookList is {}", sortedBookList);
        assertEquals(CommonEnum.BookType.EFFECTIVE_JAVA.getName(), sortedBookList.get(0).getBookName());
    }

    /**
     * Test resource build
     * @return
     */
    static {
        books.add(CommonEnum.BookType.build(CommonEnum.BookType.ANNA_KARENINA));
        books.add(CommonEnum.BookType.build(CommonEnum.BookType.BURNING_PAPER));
        books.add(CommonEnum.BookType.build(CommonEnum.BookType.DREAM_OF_THE_RED_CHAMBER));
        books.add(CommonEnum.BookType.build(CommonEnum.BookType.EFFECTIVE_JAVA));
        books.add(CommonEnum.BookType.build(CommonEnum.BookType.FORTRESS_BESIEGED));
        books.add(CommonEnum.BookType.build(CommonEnum.BookType.GONE_WITH_THE_WIND));
        books.add(CommonEnum.BookType.build(CommonEnum.BookType.LINUX));
        books.add(CommonEnum.BookType.build(CommonEnum.BookType.RECORDS_OF_THE_HISTORIAN));
        books.add(CommonEnum.BookType.build(CommonEnum.BookType.VIRTUAL_MACHINE_JAVA));
    }
}
