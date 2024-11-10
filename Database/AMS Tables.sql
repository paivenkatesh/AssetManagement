-- Create the database
CREATE DATABASE asset_management;
USE asset_management;

-- Create the role table
CREATE TABLE role (
    role_id INT AUTO_INCREMENT PRIMARY KEY,
    role_name VARCHAR(50) UNIQUE NOT NULL
);

-- Insert roles
INSERT INTO role (role_name) VALUES ('Admin'), ('User');

-- Create the employee table
CREATE TABLE employee (
    employee_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    gender ENUM('Male', 'Female', 'Other') NOT NULL,
    contact_number VARCHAR(15) NOT NULL,
    address VARCHAR(255) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    role_id INT NOT NULL,
    FOREIGN KEY (role_id) REFERENCES role(role_id)
        ON DELETE RESTRICT
        ON UPDATE CASCADE
);

-- Create the asset_category table
CREATE TABLE asset_category (
    category_id INT AUTO_INCREMENT PRIMARY KEY,
    category_name VARCHAR(100) UNIQUE NOT NULL
);

-- Insert asset categories
INSERT INTO asset_category (category_name) VALUES
('Laptop'), ('Furniture'), ('Car'), ('Gadgets');

-- Create the asset table
CREATE TABLE asset (
    asset_id INT AUTO_INCREMENT PRIMARY KEY,
    asset_name VARCHAR(100) NOT NULL,
    category_id INT NOT NULL,
    asset_model VARCHAR(50),
    manufacturing_date DATE,
    expiry_date DATE,
    asset_value DECIMAL(10, 2) NOT NULL,
    status ENUM('Available', 'Borrowed') DEFAULT 'Available',
    FOREIGN KEY (category_id) REFERENCES asset_category(category_id)
        ON DELETE RESTRICT
        ON UPDATE CASCADE
);

-- Create the asset_borrowing table
CREATE TABLE asset_borrowing (
    borrowing_id INT AUTO_INCREMENT PRIMARY KEY,
    employee_id INT,
    asset_id INT,
    borrowed_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    returned_at TIMESTAMP,
    status ENUM('Active', 'Returned') DEFAULT 'Active',
    FOREIGN KEY (employee_id) REFERENCES employee(employee_id)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
    FOREIGN KEY (asset_id) REFERENCES asset(asset_id)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);

-- Create the issue_type table
CREATE TABLE issue_type (
    issue_type_id INT AUTO_INCREMENT PRIMARY KEY,
    issue_type_name VARCHAR(100) UNIQUE NOT NULL
);

-- Insert issue types
INSERT INTO issue_type (issue_type_name) VALUES
('Hardware Failure'), ('Software Issue'), ('Routine Maintenance');

-- Create the service_request table
CREATE TABLE service_request (
    service_request_id INT AUTO_INCREMENT PRIMARY KEY,
    employee_id INT,
    asset_id INT,
    description TEXT NOT NULL,
    issue_type_id INT NOT NULL,
    status ENUM('Pending', 'In Progress', 'Completed') DEFAULT 'Pending',
    requested_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (employee_id) REFERENCES employee(employee_id)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
    FOREIGN KEY (asset_id) REFERENCES asset(asset_id)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
    FOREIGN KEY (issue_type_id) REFERENCES issue_type(issue_type_id)
        ON DELETE RESTRICT
        ON UPDATE CASCADE
);

-- Create the asset_audit table
CREATE TABLE asset_audit (
    audit_id INT AUTO_INCREMENT PRIMARY KEY,
    employee_id INT,
    asset_id INT,
    audit_status ENUM('Pending', 'Verified', 'Rejected') DEFAULT 'Pending',
    requested_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (employee_id) REFERENCES employee(employee_id)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
    FOREIGN KEY (asset_id) REFERENCES asset(asset_id)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);
