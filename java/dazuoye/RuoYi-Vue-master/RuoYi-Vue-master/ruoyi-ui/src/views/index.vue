<template>
  <div class="app-container">
    <!-- Hero Section -->
    <section class="hero-section">
      <div class="hero-content">
        <h1 class="title" v-motion-fade-in-left>医保中药目录后台管理系统</h1>
        <p class="description">西安石油大学Java课程大作业 - 软件工程2201 黄少政 202207071102</p>
      </div>
      <!--<img :src="highlightImagePath" alt="项目亮点图" class="highlight1-image" />-->
    </section>


    <!-- Tech Stack Section -->
    <section class="tech-stack-section">
      <div class="container">
        <h2 class="section-title">技术选型</h2>
        <div class="tech-grid">
          <article class="tech-card">
            <header>
              <h3><i class="fas fa-server"></i> 后端技术</h3>
            </header>
            <ul>
              <li v-for="tech in backendTech" :key="tech" @mouseover="hoverItem = tech" @mouseleave="hoverItem = ''">
                <span :class="{ 'tech-highlight': hoverItem === tech }"><i class="fas fa-check-circle"></i> {{ tech }}</span>
              </li>
            </ul>
          </article>
          <article class="tech-card">
            <header>
              <h3><i class="fas fa-laptop"></i> 前端技术</h3>
            </header>
            <ul>
              <li v-for="tech in frontendTech" :key="tech" @mouseover="hoverItem = tech" @mouseleave="hoverItem = ''">
                <span :class="{ 'tech-highlight': hoverItem === tech }"><i class="fas fa-check-circle"></i> {{ tech }}</span>
              </li>
            </ul>
          </article>
        </div>
      </div>
    </section>

    <!-- Additional Features Section -->
    <section class="additional-features-section">
      <div class="container">
  <h2 class="section-title">系统特性概览</h2>
  <div class="features-grid">
    <!-- 整体界面构造 -->
    <div class="feature-item">
      <i class="fas fa-layer-group"></i>
      <h4>整体界面构造</h4>
      <p>精心设计的用户界面，确保直观、友好且响应式，适用于各种设备。</p>
    </div>
    <!-- 数据库连接编程 -->
    <div class="feature-item">
      <i class="fas fa-database"></i>
      <h4>数据库连接编程</h4>
      <p>高效稳定的数据库连接管理，采用Druid连接池和MyBatis框架优化数据访问。</p>
    </div>
    <!-- 功能实现正确和完整度 -->
    <div class="feature-item">
      <i class="fas fa-check-circle"></i>
      <h4>功能实现正确和完整度</h4>
      <p>所有业务逻辑严格测试，确保功能实现的准确性和完整性。</p>
    </div>
    <!-- 组件应用情况 -->
    <div class="feature-item">
      <i class="fas fa-puzzle-piece"></i>
      <h4>组件应用情况</h4>
      <p>广泛使用Vue.js等前端组件，构建灵活可复用的UI元素。</p>
    </div>
    <!-- 数据正确性检查 -->
    <div class="feature-item">
      <i class="fas fa-shield-alt"></i>
      <h4>数据正确性检查</h4>
      <p>内置数据库信息验证机制，确保输入数据的有效性和一致性。</p>
    </div>
    <!-- 查询功能丰富程度 -->
    <div class="feature-item">
      <i class="fas fa-search-plus"></i>
      <h4>查询功能丰富程度</h4>
      <p>提供多种高级搜索选项，支持复杂条件查询，满足多样化的检索需求。</p>
    </div>
    <!-- 异常的捕捉处理 -->
    <div class="feature-item">
      <i class="fas fa-bug"></i>
      <h4>异常的捕捉处理</h4>
      <p>全面的日志记录与错误处理机制，快速定位并解决系统问题。</p>
    </div>
    <!-- 是否有特色功能 -->
    <div class="feature-item">
      <i class="fas fa-star"></i>
      <h4>特色功能</h4>
      <p>多用户信息管理  不同用户角色权限管理   </p>
    </div>
  </div>
</div>
    </section>

    <!-- System Architecture Overview Section -->
    <section class="system-architecture-section">
      <div class="container">
        <h2 class="section-title">系统架构概览</h2>
        <!--<img src="https://example.com/architecture.png" alt="系统架构图" class="architecture-img" />-->
        <p class="architecture-description">
本医保中药目录后台管理系统采用分层架构，确保高效、稳定和可扩展。

1. 表现层：使用Vue等技术构建用户界面，提供直观友好的交互体验，适配多种终端设备。
   
2. 业务逻辑层：基于Spring Boot框架处理业务规则，结合Spring Security和JWT技术保障数据安全性和访问合法性，支持多用户角色管理。

3. 数据访问层：通过MyBatis框架实现数据库操作与Java代码映射，利用Druid优化数据库连接资源，提高性能。

4. 数据存储层：选用合适数据库存储核心数据，遵循规范化设计原则，并通过云端备份策略确保数据安全和可恢复性。

各层级间通信接口清晰，遵循松耦合原则，便于维护和扩展，确保系统灵活性和适应性。
</p>
      </div>
    </section>

    <!-- Additional Design Elements Section -->
    <section class="design-elements-section">
      <div class="container">
        <h2 class="section-title">更多设计元素</h2>
        <div class="elements-grid">
          <div class="element-item">
            <i class="fas fa-code"></i>
            <h4>代码质量</h4>
            <p>高质量的代码是成功的基础。</p>
          </div>
          <div class="element-item">
            <i class="fas fa-chart-line"></i>
            <h4>数据分析</h4>
            <p>强大的数据分析功能助力决策。</p>
          </div>
          <div class="element-item">
            <i class="fas fa-cogs"></i>
            <h4>系统配置</h4>
            <p>灵活的系统配置满足不同需求。</p>
          </div>
        </div>
      </div>
    </section>

    <!-- Footer Section -->
    <footer class="footer-section">
      <p>&copy; 2024 医保中药目录后台管理系统. All rights reserved.</p>
    </footer>
  </div>
</template>

<script>
export default {
  data() {
    return {
      highlightImagePath: require('@/assets/images/highlight.jpg'),
      backendTech: ['SpringBoot', 'Spring Security', 'JWT', 'MyBatis', 'Druid', 'Fastjson'],
      frontendTech: ['Vue', 'Vuex', 'Element-ui', 'Axios', 'Sass', 'Quill'],
      hoverItem: ''
    };
  }
};
</script>

<style scoped>
@import url('https://fonts.googleapis.com/css2?family=Amatic+SC:wght@700&family=Noto+Sans+SC:wght@300;400;700&display=swap');
@import url('https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css');

.app-container {
  font-family: 'Noto Sans SC', sans-serif;
  max-width: 1400px;
  margin: 0 auto;
  padding: 0 20px;
}

.hero-section {
  background: linear-gradient(135deg, #6dd5ed, #2193b0);
  color: #fff;
  padding: 120px 20px;
  text-align: center;
  position: relative;
  border-radius: 0 0 50% 50%;
  overflow: hidden;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}

.hero-content {
  z-index: 2;
  position: relative;
  max-width: 800px;
}

.title {
  font-size: 3.5rem;
  margin-bottom: 1rem;
  animation: fadeInUp 1s ease-out;
  font-family: 'Amatic SC', cursive;
  letter-spacing: 2px;
}

.description {
  font-size: 1.4rem;
  margin-bottom: 2rem;
  font-weight: 300;
}

.highlight-image {
  width: 100%;
  max-width: 500px;
  height: auto;
  position: absolute;
  bottom: -120px;
  left: 50%;
  transform: translateX(-50%);
  opacity: 0.8;
}

.illustration {
  width: 100%;
  max-width: 350px;
  margin-top: 2rem;
}

.tech-stack-section {
  padding: 5rem 2rem;
  background-color: #ecf0f1;
  background-image: radial-gradient(circle at 50% 50%, rgba(255, 255, 255, 0.5), transparent);
  background-blend-mode: overlay;
}

.container {
  max-width: 1200px;
  margin: 0 auto;
}

.section-title {
  text-align: center;
  font-size: 2.8rem;
  margin-bottom: 2.5rem;
  color: #2c3e50;
  font-family: 'Amatic SC', cursive;
}

.tech-grid {
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(300px, 1fr));
  gap: 3rem;
}

.tech-card {
  background-color: #fff;
  border-radius: 16px;
  box-shadow: 0 4px 16px rgba(0, 0, 0, 0.1);
  padding: 2rem;
  transition: all 0.3s ease;
  position: relative;
}

.tech-card::before {
  content: '';
  position: absolute;
  top: -10px;
  left: -10px;
  right: -10px;
  bottom: -10px;
  border-radius: 20px;
  background: rgba(255, 255, 255, 0.2);
  z-index: -1;
}

.tech-card:hover {
  transform: translateY(-10px);
  box-shadow: 0 8px 32px rgba(0, 0, 0, 0.1);
}

.tech-card header h3 {
  margin-bottom: 1.2rem;
  color: #3498db;
  display: flex;
  align-items: center;
  font-family: 'Amatic SC', cursive;
}

.tech-card ul {
  list-style-type: none;
  padding: 0;
}

.tech-card li {
  background-color: #ecf0f1;
  margin: 0.8rem 0;
  padding: 0.8rem;
  border-radius: 16px;
  transition: all 0.3s ease;
  cursor: pointer;
}

.tech-card li:hover,
.tech-highlight {
  background-color: #e67e22!important;
  color: white;
}

.fas {
  margin-right: 0.6rem;
}

.additional-features-section {
  padding: 5rem 2rem;
  background-color: #d7ebf9;
}

.features-grid {
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(300px, 1fr));
  gap: 3rem;
}

.feature-item {
  background-color: #fff;
  border-radius: 16px;
  padding: 2rem;
  box-shadow: 0 4px 16px rgba(0, 0, 0, 0.1);
  transition: all 0.3s ease;
  position: relative;
}

.feature-item::before {
  content: '';
  position: absolute;
  top: -10px;
  left: -10px;
  right: -10px;
  bottom: -10px;
  border-radius: 20px;
  background: rgba(255, 255, 255, 0.2);
  z-index: -1;
}

.feature-item:hover {
  transform: translateY(-10px);
  box-shadow: 0 8px 32px rgba(0, 0, 0, 0.1);
}

.feature-item i {
  font-size: 2.5rem;
  color: #3498db;
  margin-bottom: 1.2rem;
}

.feature-item h4 {
  font-size: 1.8rem;
  color: #2c3e50;
  margin-bottom: 0.8rem;
}

.system-architecture-section {
  padding: 5rem 2rem;
  background-color: #f4f9fb;
  text-align: center;
}

.architecture-img {
  width: 100%;
  max-width: 800px;
  height: auto;
  margin-bottom: 2rem;
  border-radius: 16px;
}

.architecture-description {
  font-size: 1.4rem;
  color: #555;
  line-height: 1.6;
  max-width: 800px;
  margin: 0 auto;
}

.design-elements-section {
  padding: 5rem 2rem;
  background-color: #dfe6e9;
  text-align: center;
}

.elements-grid {
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(300px, 1fr));
  gap: 3rem;
}

.element-item {
  background-color: #fff;
  border-radius: 16px;
  padding: 2.5rem;
  box-shadow: 0 4px 16px rgba(0, 0, 0, 0.1);
  transition: all 0.3s ease;
  position: relative;
}

.element-item::before {
  content: '';
  position: absolute;
  top: -10px;
  left: -10px;
  right: -10px;
  bottom: -10px;
  border-radius: 20px;
  background: rgba(255, 255, 255, 0.2);
  z-index: -1;
}

.element-item:hover {
  transform: translateY(-10px);
  box-shadow: 0 8px 32px rgba(0, 0, 0, 0.1);
}

.element-item i {
  font-size: 2.5rem;
  color: #3498db;
  margin-bottom: 1.2rem;
}

.element-item h4 {
  font-size: 1.8rem;
  color: #2c3e50;
  margin-bottom: 0.8rem;
}

.footer-section {
  padding: 3rem;
  text-align: center;
  background-color: #eaf2f8; /* 更淡的底部颜色 */
  color: #2c3e50;
  font-family: 'Amatic SC', cursive;
}

/* Animation */
@keyframes fadeInUp {
  from {
    opacity: 0;
    transform: translateY(20px);
  }
  to {
    opacity: 1;
    transform: translateY(0);
  }
}
</style>