
// 일반 사용자 테이블
CREATE TABLE users (
  user_id VARCHAR(20) PRIMARY KEY,
  user_password VARCHAR(20) NOT NULL,
  user_name VARCHAR(10) NOT NULL,
  user_phone VARCHAR(13) NOT NULL UNIQUE,  
user_account VARCHAR(20) NOT NULL UNIQUE,
  reg_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

// 사업자 테이블
CREATE TABLE clients(
  client_id VARCHAR(20) PRIMARY KEY,
  client_password VARCHAR(20) NOT NULL,
  client_name VARCHAR(10) NOT NULL,
  client_phone VARCHAR(13) NOT NULL UNIQUE,
  client_account VARCHAR(20) NOT NULL UNIQUE,
  client_number VARCHAR(20) NOT NULL UNIQUE,
  reg_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP);
  
  // 일반 사용자 계좌
  CREATE TABLE user_accounts (
  account_code INT PRIMARY KEY AUTO_INCREMENT,
  user_account VARCHAR(20) NOT NULL UNIQUE,
  user_credit int(20) NOT NULL default 0,
  user_id VARCHAR(20),
  reg_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE
) AUTO_INCREMENT=10001;

// 사업자 계좌
  CREATE TABLE client_account (
  account_code INT PRIMARY KEY AUTO_INCREMENT,
 client_account VARCHAR(20) NOT NULL UNIQUE,
 client_credit int(10) NOT NULL DEFAULT 0,
 client_id VARCHAR(20),
 reg_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
 FOREIGN KEY (client_id) REFERENCES clients(client_id) ON DELETE CASCADE
)AUTO_INCREMENT=10001;

// 주차장 
CREATE TABLE location (
location_code int PRIMARY KEY AUTO_INCREMENT,
max_area_count int(3) NOT NULL,
current_area_count int(3) DEFAULT 0,
location_address VARCHAR(20) NOT NULL,
location_name VARCHAR(10) NOT NULL,
client_id VARCHAR(20) ,
check_disabled_area BOOLEAN,
 FOREIGN KEY (client_id) REFERENCES clients(client_id) ON DELETE CASCADE
)AUTO_INCREMENT=1;

//주차 구역

CREATE TABLE parking_spot (
spot_code int PRIMARY KEY AUTO_INCREMENT,
spot_name VARCHAR(20) NOT NULL,
location_code int,
spot_cost int(5) NOT NULL,
check_spot BOOLEAN default true,
disabled_spot BOOLEAN default true,
FOREIGN KEY (location_code) REFERENCES location(location_code) ON DELETE CASCADE
);

// 게시판 테이블 생성
CREATE TABLE board(
board_number int PRIMARY KEY,
board_title VARCHAR(30) NOT NULL,
board_contents VARCHAR(3000) NOT NULL,
user_id VARCHAR(20),
reg_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
view_count int DEFAULT 0 NOT NULL,
FOREIGN KEY (user_id) REFERENCES users(user_id)
);

// 공지사항 테이블

CREATE TABLE notice(
notice_number int PRIMARY KEY AUTO_INCREMENT,
notice_title VARCHAR(30) NOT NULL,
notice_contents VARCHAR(3000) NOT NULL,
reg_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
view_count int DEFAULT 0 NOT NULL
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 AUTO_INCREMENT = 1;

// booking table
CREATE TABLE booking(
booking_code int PRIMARY KEY AUTO_INCREMENT,
user_id VARCHAR(20),
location_code int,
spot_code  int NOT NULL,
car_number VARCHAR(10) NOT NULL,
start_time TIMESTAMP NOT NULL,
end_time TIMESTAMP ,
reg_date TIMESTAMP NOT DEFAULT CURRENT_TIMESTAMP ,
total_cost int(10) NOT NULL,
check_payment boolean,
FOREIGN KEY (user_id) REFERENCES users(user_id),
FOREIGN KEY (location_code) REFERENCES location(location_code),
FOREIGN KEY (spot_code) REFERENCES parking_spot(spot_code)
) AUTO_INCREMENT=1;

// 부킹 내역 조회 뷰 테이블 생성
CREATE VIEW booking_info_all AS
SELECT users.user_name, location.location_code,location.location_name, location.location_address,parking_spot.spot_name, booking.user_id, booking.booking_code, booking.car_number, booking.start_time, booking.end_time, booking.reg_date, booking.total_cost, booking.check_payment,parking_spot.spot_code
FROM booking
JOIN users ON booking.user_id = users.user_id
JOIN location ON booking.location_code = location.location_code
JOIN parking_spot ON booking.spot_code = parking_spot.spot_code;

