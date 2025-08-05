# 🌟 Durgasoft Spring JDBC DAO Module 🛠️  
### *Crafted by [Nagoor Babu]*

<div align="center">
  <img src="https://img.shields.io/badge/Spring_JDBC-6DB33F?style=for-the-badge&logo=spring&logoColor=white" />
  <img src="https://img.shields.io/badge/Java-17-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white" />
  <img src="https://img.shields.io/badge/MySQL-4479A1?style=for-the-badge&logo=mysql&logoColor=white" />
  <img src="https://img.shields.io/badge/DAO_Pattern-FF6F61?style=for-the-badge" />
</div>

Repository demonstrating **Spring JDBC** implementations with **DAO (Data Access Object)** pattern, covering core database operations in Spring.

---

## 🏆 Key Concepts  
### 📂 Project Listings 🎯  

| S.No | Project | Description | Key Features |
|------|---------|-------------|--------------|
| 1 | [📦 Basic JDBC Setup](basic-jdbc-setup/) | Core Spring JDBC configuration | `JdbcTemplate` • `DataSource` |
| 2 | [📝 CRUD Operations](crud-operations/) | Create, Read, Update, Delete | `update()` • `query()` • `batchUpdate()` |
| 3 | [🧩 DAO Interface](dao-interface/) | Standard DAO pattern | `EmployeeDao` • `@Repository` |
| 4 | [🔧 RowMapper Implementation](rowmapper-implementation/) | Custom row mapping | `RowMapper<T>` • `BeanPropertyRowMapper` |
| 5 | [🏗️ NamedParameterJdbcTemplate](named-parameter-jdbc/) | Named parameter queries | `NamedParameterJdbcTemplate` • `MapSqlParameterSource` |
| 6 | [✨ SimpleJdbcInsert](simple-jdbc-insert/) | Simplified inserts | `SimpleJdbcInsert` • `execute()` |
| 7 | [🔄 Transaction Management](transaction-management/) | ACID operations | `@Transactional` • `PlatformTransactionManager` |
| 8 | [📜 XML Configuration](xml-configuration/) | XML-based JDBC setup | `<bean>` • `<tx:annotation-driven>` |
| 9 | [☕ Java Config](java-configuration/) | Annotation-driven config | `@Configuration` • `@Bean` |

---

## 🚀 Quick Start  
1. **Clone the repo**:  
   ```bash
   git clone https://github.com/safi-sfn/spring_jdbc_dao_mod.git
