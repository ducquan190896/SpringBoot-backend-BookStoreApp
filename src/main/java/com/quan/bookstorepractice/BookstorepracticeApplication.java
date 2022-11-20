package com.quan.bookstorepractice;

import java.time.Year;
import java.util.Date;
import java.util.Optional;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.quan.bookstorepractice.Entity.Account;
import com.quan.bookstorepractice.Entity.Book;
import com.quan.bookstorepractice.Entity.Category;
import com.quan.bookstorepractice.Entity.Role;
import com.quan.bookstorepractice.Entity.RoleType;
import com.quan.bookstorepractice.Repository.AccountRepo;
import com.quan.bookstorepractice.Repository.BookRepo;
import com.quan.bookstorepractice.Repository.CategoryRepo;
import com.quan.bookstorepractice.Repository.RoleRepo;

@SpringBootApplication
public class BookstorepracticeApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookstorepracticeApplication.class, args);
	}
	
	@Bean
	CommandLineRunner commandLineRunner(CategoryRepo categoryRepo, BookRepo bookRepo, AccountRepo accountRepo, RoleRepo roleRepo) {
		return args -> {
			categoryRepo.save(new Category("Fiction"));
			categoryRepo.save(new Category("Non-fiction"));
			categoryRepo.save(new Category("Poetry"));

			
			Book b1 = new Book("One", "one oneson", 2019, 100, categoryRepo.findByName("Fiction").get());
			Book b2 = new Book("Two", "Two Twoson", 2020, 400, categoryRepo.findByName("Poetry").get());
			Book b3 = new Book("Three", "Three Threeson", 2020 , 500, categoryRepo.findByName("Non-fiction").get());
			

			// Book b1 = new Book("One", "one oneson", 2019, 100, new Category("Fiction"));
			// Book b2 = new Book("Two", "Two Twoson", 2020, 400, new Category("Non-fiction"));
			// Book b3 = new Book("Three", "Three Threeson", 2020 , 500, new Category("Poetry"));
			
			bookRepo.save(b1);
			bookRepo.save(b2);
			bookRepo.save(b3);
			
			Role admin = new Role(RoleType.ADMIN);
			Role user = new Role(RoleType.USER);
			Role manager = new Role(RoleType.MANAGER);
			roleRepo.save(admin);
			roleRepo.save(user);
			roleRepo.save(manager);

			Account user1 = new Account("quan_admin", "123456", admin);
			Account user2 = new Account("quan", "123456", user);
			Account user3 = new Account("khanh", "123456", manager);

			accountRepo.save(user1);
			accountRepo.save(user2);
			accountRepo.save(user3);
			
			Optional<Role> entity = roleRepo.findByRoleType(RoleType.ADMIN.name());
			if(entity.isPresent()) {
				System.out.println(entity.get());
			} else {
				System.out.println("nothing to return");
			}

		};
	}
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean 
	public int getCurrentTime() {
		return Year.now().getValue();
	}
}
