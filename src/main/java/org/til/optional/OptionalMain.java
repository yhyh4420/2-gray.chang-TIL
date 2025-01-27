package org.til.optional;

import org.w3c.dom.ls.LSOutput;

import java.util.Optional;

public class OptionalMain {

    public static void main(String[] args) {
        Optional<Member> optionalMember = Optional.ofNullable(null);
        System.out.println(optionalMember); // Optional.empty
        System.out.println(optionalMember.isPresent()); // false

        System.out.println("------------------------");

        Optional<Member> optionalMember2 = Optional.of(new Member("test1", 10));
        System.out.println(optionalMember2.get().toString());

        System.out.println("------------------------");

        optionalMember.ifPresentOrElse(
                value -> System.out.println("값이 있습니다. \n" + value.toString()),
                () -> System.out.println("값이 없습니다."));
        optionalMember2.ifPresentOrElse(
                value -> System.out.println("값이 있습니다. :\n" + value.toString()),
                () -> System.out.println("값이 없습니다."));
    }
}
