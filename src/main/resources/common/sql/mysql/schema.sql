create table member (
      id INT(11) UNSIGNED AUTO_INCREMENT PRIMARY KEY,
      user_name varchar(255) not null,
      phone varchar(255) not null,
      created_at DATETIME(6) DEFAULT CURRENT_TIMESTAMP(6),
      created_by varchar(255),
      last_modified_at DATETIME(6) DEFAULT CURRENT_TIMESTAMP(6),
      last_modified_by varchar(255),
      primary key (id)
);

create table member_social_login (
        id INT(11) UNSIGNED AUTO_INCREMENT PRIMARY KEY,
        provider varchar(255) not null,
        email varchar(255) not null,
        created_at DATETIME(6) DEFAULT CURRENT_TIMESTAMP(6),
        created_by varchar(255),
        last_modified_at DATETIME(6) DEFAULT CURRENT_TIMESTAMP(6),
        last_modified_by varchar(255),
        primary key (id)
);
