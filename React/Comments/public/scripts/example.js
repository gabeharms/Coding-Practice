
// Comment Box class
var CommentBox = React.createClass({
  getInitialState: function() {    // executes exactly once during the lifecycle of 
    return {data: []};             // the component and sets up the initial state of the component.
  },
  loadCommentsFromServer: function() {  
    $.ajax({                       
      url: this.props.url,
      dataType: 'json',
      cache: false,
      success: function(data) {
        this.setState({data: data});
      }.bind(this),
      error: function(xhr, status, err) {
        console.error(this.props.url, status, err.toString());
      }.bind(this)
    });
  },
  componentDidMount: function() {   // method called automatically by React after a 
    this.loadCommentsFromServer();  // component is rendered for the first time.
    setInterval(this.loadCommentsFromServer, this.props.pollInterval);
  },
  handleCommentSubmit: function(comment) {    // We need to pass data from the child 
                                              // component back up to its parent. We do 
                                              // this in our parent's render method by passing 
  },                                          // a new callback (handleCommentSubmit) into 
                                              // the child, binding it to the child's onCommentSubmit event.

  render: function() { // Only function in the 'CommentBox' class
    return (
      <div className="commentBox">
        <h1>Comments</h1>
        <CommentList data={this.state.data} />
        <CommentForm onCommentSubmit={this.handleCommentSubmit} />
      </div>              
    ); // The JSX compiler will automatically rewrite HTML tags 
  }    // to React.createElement(tagName) expressions and leave everything else alone.
});    // This is to prevent the pollution of the global namespace.

// Comment list Class
var CommentList = React.createClass({
  render: function() {
    var commentNodes = this.props.data.map(function(comment) {
      return (
        <Comment author={comment.author} key={comment.id}>
          {comment.text}
        </Comment>
      );
    });
    return (
      <div className="commentList">
        {commentNodes}
      </div>
    );
  }
});

// Comment Form Class
var CommentForm = React.createClass({
  getInitialState: function() {
    return {author: '', text: ''};
  },
  handleAuthorChange: function(e) {
    this.setState({author: e.target.value});
  },
  handleTextChange: function(e) {
    this.setState({text: e.target.value});
  },
  handleSubmit: function(e) {
    e.preventDefault();
    var author = this.state.author.trim();
    var text = this.state.text.trim();
    if (!text || !author) {
      return;
    }
    this.props.onCommentSubmit({author: author, text: text});  // Callback of the parent element
    this.setState({author: '', text: ''});
  },
  render: function() {
    return (
      <form className="commentForm" onSubmit={this.handleSubmit}>
        <input
          type="text"
          placeholder="Your name"
          value={this.state.author}
          onChange={this.handleAuthorChange}
        />
        <input
          type="text"
          placeholder="Say something..."
          value={this.state.text}
          onChange={this.handleTextChange}
        />
        <input type="submit" value="Post" />
      </form>
    );
  }    // as the user enters text into the <input> fields, the attached 
});    // onChange callbacks are fired and the state of the component is modified. 
       // Subsequently, the rendered value of the input element will be updated to 
       // reflect the current component state.


// Comment Class
var Comment = React.createClass({
  render: function() {
    return (
      <div className="comment">
        <h2 className="commentAuthor"> 
          {this.props.author}          
        </h2>                          
        {this.props.children}
      </div>
    );     // Data passed in from a parent component is available as a 'property' on 
  }        // the child component. These 'properties' are accessed through this.props.
});        // Using props, we will be able to read the data passed to the Comment from the CommentList


// Instantiates the root component, starts the framework, 
// and injects the markup into a raw DOM element, provided as the second argument.
//
// It is important that ReactDOM.render remain at the bottom of the script for
// this tutorial. ReactDOM.render should only be called after the composite components
// have been defined.
ReactDOM.render(
  <CommentBox url="/api/comments" pollInterval={2000} />,
  document.getElementById('content')
);