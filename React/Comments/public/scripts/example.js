
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
  render: function() { // Only function in the 'CommentBox' class
    return (
      <div className="commentBox">
        <h1>Comments</h1>
        <CommentList data={this.state.data} />
        <CommentForm />   
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
  render: function() {
    return (
      <div className="commentForm">
        Hello, world! I am a CommentForm.
      </div>
    );
  }
});

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
  <CommentBox url="/api/comments" />,
  document.getElementById('content')
);