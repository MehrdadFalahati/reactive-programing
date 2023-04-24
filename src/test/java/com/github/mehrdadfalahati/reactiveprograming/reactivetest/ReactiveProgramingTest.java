package com.github.mehrdadfalahati.reactiveprograming.reactivetest;

import com.github.mehrdadfalahati.reactiveprograming.model.Person;
import com.github.mehrdadfalahati.reactiveprograming.model.PersonCommand;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.Optional;
import java.util.concurrent.CountDownLatch;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
public class ReactiveProgramingTest {

    private Person mehrdad = new Person("mehrdad", "falahati");
    private Person mehdi = new Person("mehdi", "falahati");
    private Person hadi = new Person("hadi", "falahati");
    private Person erfan = new Person("erfan", "goldozian");
    private Person omid = new Person("omid", "bidkham");

    @Test
    public void testMonoPerson() {
        Mono<Person> person = Mono.just(mehrdad);

        final Person mehrdadPerson = person.block();

        log.info("Person is => {}", mehrdadPerson);
        assertEquals(mehrdad, mehrdadPerson);
    }

    @Test
    public void monoTransform() throws Exception {
        //create new person mono
        Mono<Person> personMono = Mono.just(mehrdad);

        PersonCommand command = personMono
                .map(PersonCommand::new).block();

        log.info("Person command is => {}", command);
        assertEquals(mehrdad.getFirstName(), command.getFirstName());
    }

    @Test
    public void monoFilter() throws Exception {
        Mono<Person> personMono = Mono.just(mehdi);

        //filter example
        Person samAxe = personMono
                .filter(person -> person.getFirstName().equalsIgnoreCase("foo"))
                .block();


        assertThrows(NullPointerException.class, () -> samAxe.getFirstName());
    }

    @Test
    public void fluxTest() throws Exception {

        Flux<Person> people = Flux.just(mehrdad, mehdi, hadi, omid);

        people.subscribe(person -> log.info("Person is => {}", person));

    }

    @Test
    public void fluxTestFilter() throws Exception {

        Flux<Person> people = Flux.just(mehrdad, mehdi, hadi, omid, erfan);

        people.filter(person -> person.getFirstName().equals(mehdi.getFirstName()))
                .subscribe(person -> log.info("Person is => {}", person));

    }

    @Test
    public void fluxTestDelayNoOutput() throws Exception {

        Flux<Person> people = Flux.just(mehrdad, mehdi, hadi, omid, erfan);

        people.delayElements(Duration.ofSeconds(1))
                .subscribe(person -> log.info("Person is => {}", person));

    }

    @Test
    public void fluxTestDelay() throws Exception {

        CountDownLatch countDownLatch = new CountDownLatch(1);

        Flux<Person> people = Flux.just(mehrdad, mehdi, hadi, omid, erfan);

        people.delayElements(Duration.ofSeconds(1))
                .doOnComplete(countDownLatch::countDown)
                .subscribe(person -> log.info("Person is => {}", person));

        countDownLatch.await();

    }

    @Test
    public void fluxTestFilterDelay() throws Exception {

        CountDownLatch countDownLatch = new CountDownLatch(1);

        Flux<Person> people = Flux.just(mehrdad, mehdi, hadi, omid, erfan);

        people.delayElements(Duration.ofSeconds(1))
                .filter(person -> person.getFirstName().contains("i"))
                .doOnComplete(countDownLatch::countDown)
                .subscribe(person -> log.info("Person is => {}", person));

        countDownLatch.await();
    }

    @Test
    public void test() {
        assertTrue(checkIsPersent(true).isPresent());
        assertFalse(checkIsPersent(false).isPresent());
    }

    private Optional<String> checkIsPersent(boolean validation) {
        if (validation)
            return Optional.of("hi");
        return Optional.empty();
    }
}
